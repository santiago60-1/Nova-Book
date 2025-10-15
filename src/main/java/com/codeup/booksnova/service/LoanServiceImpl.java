// src/main/java/com/codeup/booksnova/service/LoanServiceImpl.java
package com.codeup.booksnova.service;

import com.codeup.booksnova.dao.BookDAO;
import com.codeup.booksnova.dao.LoanDAO;
import com.codeup.booksnova.dao.PartnerDAO;
import com.codeup.booksnova.dao.BookDaoJdbc;
import com.codeup.booksnova.dao.LoanDaoJdbc;
import com.codeup.booksnova.dao.PartnerDaoJdbc;
import com.codeup.booksnova.domain.Book;
import com.codeup.booksnova.domain.Loan;
import com.codeup.booksnova.domain.Partner;
import com.codeup.booksnova.util.ConnectionFactory;

import java.math.BigDecimal;
import java.sql.Connection;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Optional;

public class LoanServiceImpl implements LoanService {

    private final BookDAO bookDao      = new BookDaoJdbc();
    private final PartnerDAO partnerDao = new PartnerDaoJdbc();
    private final LoanDAO loanDao      = new LoanDaoJdbc();

    // Read business rules from config.properties (keys must be updated to English)
    private final int loanPeriodDays = Integer.parseInt(
        ConnectionFactory.get("loanPeriodDays")
    );
    private final BigDecimal finePerDay = new BigDecimal(
        ConnectionFactory.get("finePerDay")
    );

    @Override
    public Loan issueLoan(int bookId, int partnerId) throws Exception {
        Connection conn = ConnectionFactory.getConnection();
        try {
            conn.setAutoCommit(false);

            // 1. Load and update Book
            Book book = bookDao.findById(bookId)
                .orElseThrow(() -> new IllegalArgumentException("Book not found"));
            if (book.getTotalCopies() < 1) {
                throw new IllegalStateException("No copies available");
            }
            book.setTotalCopies(book.getTotalCopies() - 1);
            bookDao.update(book);

            // 2. Load Partner
            Partner partner = partnerDao.findById(partnerId)
                .orElseThrow(() -> new IllegalArgumentException("Partner not found"));

            // 3. Create Loan record
            LocalDate today = LocalDate.now();
            LocalDate dueDate = today.plusDays(loanPeriodDays);
            Loan loan = new Loan(
                null, 
                book, 
                partner, 
                today, 
                dueDate,
                BigDecimal.ZERO, 
                "NO", 
                today.atStartOfDay(), 
                today.atStartOfDay()
            );
            loanDao.save(loan, conn);

            conn.commit();
            return loan;
        } catch (Exception e) {
            conn.rollback();
            throw e;
        } finally {
            conn.close();
        }
    }

    @Override
    public Loan returnLoan(int loanId) throws Exception {
        Connection conn = ConnectionFactory.getConnection();
        try {
            conn.setAutoCommit(false);

            // 1. Load and update Loan
            Loan loan = loanDao.findById(loanId)
                .orElseThrow(() -> new IllegalArgumentException("Loan not found"));

            LocalDate today = LocalDate.now();
            long daysLate = ChronoUnit.DAYS.between(loan.getReturnDate(), today);
            BigDecimal fine = daysLate > 0
                ? finePerDay.multiply(BigDecimal.valueOf(daysLate))
                : BigDecimal.ZERO;

            loan.setReturnDate(today);
            loan.setFine(fine);
            loan.setDevuelto("YES");
            loanDao.update(loan, conn);

            // 2. Restore Book copy
            Book book = bookDao.findById(loan.getBook().getId())
                .orElseThrow(() -> new IllegalArgumentException("Book not found"));
            book.setTotalCopies(book.getTotalCopies() + 1);
            bookDao.update(book);

            conn.commit();
            return loan;
        } catch (Exception e) {
            conn.rollback();
            throw e;
        } finally {
            conn.close();
        }
    }

    @Override
    public Optional<Loan> findById(int loanId) throws Exception {
        return loanDao.findById(loanId);
    }
}
