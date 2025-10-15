// src/main/java/com/codeup/booksnova/service/LoanService.java
package com.codeup.booksnova.service;

import com.codeup.booksnova.domain.Loan;

import java.util.Optional;

/**
 * Business operations for issuing and returning loans.
 */
public interface LoanService {
    /**
     * Issue a new loan:
     *  - decrements the book’s available copies
     *  - creates a loan record with a due date
     *
     * @param bookId    the ID of the book to loan
     * @param partnerId the ID of the partner borrowing the book
     * @return the created Loan
     * @throws Exception on validation or JDBC errors
     */
    Loan issueLoan(int bookId, int partnerId) throws Exception;

    /**
     * Process a loan return:
     *  - calculates and applies any late fine
     *  - increments the book’s available copies
     *
     * @param loanId the ID of the loan being returned
     * @return the updated Loan with return info
     * @throws Exception on validation or JDBC errors
     */
    Loan returnLoan(int loanId) throws Exception;

    /**
     * Find a loan by its ID.
     *
     * @param loanId the ID of the loan
     * @return Optional containing the Loan if found
     * @throws Exception on JDBC errors
     */
    Optional<Loan> findById(int loanId) throws Exception;
}