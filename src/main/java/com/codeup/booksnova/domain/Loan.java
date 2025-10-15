package com.codeup.booksnova.domain;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * Maps to the loans table in the database.
 */
public class Loan {
    private Integer id;
    private Book book;
    private Partner partner;
    private LocalDate issueLoan;
    private LocalDate returnDate;
    private BigDecimal fine;
    private String devuelto;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    /**
     * All-args constructor.
     * Calls setters so each value is checked before assignment.
     */
    public Loan(Integer id, Book book, Partner partner, LocalDate issueLoan, LocalDate returnDate, BigDecimal fine, String devuelto, LocalDateTime createdAt, LocalDateTime updatedAt) {
        setId(id);
        setBook(book);
        setPartner(partner);
        setIssueLoan(issueLoan);
        setReturnDate(returnDate);
        setFine(fine);
        setDevuelto(devuelto);
        setCreatedAt(createdAt);
        setUpdatedAt(updatedAt);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        // id must be positive if not null
        if (id != null && id < 1) {
            throw new IllegalArgumentException("id must be positive");
        }
        this.id = id;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = Objects.requireNonNull(book, "book cannot be null");
    }

    public Partner getPartner() {
        return partner;
    }

    public void setPartner(Partner partner) {
        this.partner = Objects.requireNonNull(partner, "partner cannot be null");
    }

    public LocalDate getIssueLoan() {
        return issueLoan;
    }

    public void setIssueLoan(LocalDate issueLoan) {
        this.issueLoan = Objects.requireNonNull(issueLoan, "issueLoan cannot be null");
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = Objects.requireNonNull(returnDate, "returnDate cannot be null");
    }

    public BigDecimal getFine() {
        return fine;
    }

    public void setFine(BigDecimal fine) {
        this.fine = Objects.requireNonNull(fine, "fine cannot be null");
    }

    public String getDevuelto() {
        return devuelto;
    }

    public void setDevuelto(String devuelto) {
        this.devuelto = Objects.requireNonNull(devuelto, "devuelto cannot be null");
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = Objects.requireNonNull(createdAt, "createdAt cannot be null");
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = Objects.requireNonNull(updatedAt, "updatedAt cannot be null");
    }

    @Override
    public String toString() {
        // Shows key loan data for logs/debug
        return String.format(
            "Loan{id=%d, bookId=%d, partnerId=%d, issueLoan=%s, returnDate=%s, fine=%s, devuelto='%s'}",
            id,
            book.getId(),
            partner.getId(),
            issueLoan,
            returnDate,
            fine,
            devuelto
        );
    }
}