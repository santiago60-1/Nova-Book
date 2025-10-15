package com.codeup.booksnova.domain;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

public class Book {
    private Integer id;
    private String isbn;
    private String title;
    private String author;
    private String category;
    private Integer totalCopies;
    private BigDecimal referencePrice;
    private String status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;     
    
    
     /**
     * Constructor with all arguments.
     * It builds a Book object with the given values.
     * We call each setter to check the data is valid.
     *
     * @param id             book id
     * @param isbn           unique book code
     * @param title          book title
     * @param author         book author
     * @param category       book category
     * @param totalCopies    number of copies
     * @param referencePrice price reference
     * @param status         book status
     * @param createdAt      creation time
     * @param updatedAt      update time
     */
    public Book(Integer id,  String isbn, String title, String author,String category,Integer totalCopies,BigDecimal referencePrice, 
                String status, LocalDateTime createdAt,LocalDateTime updatedAt) {
        
        setId(id);
        setIsbn(isbn);
        setTitle(title);
        setAuthor(author);
        setCategory(category);
        setTotalCopies(totalCopies);
        setReferencePrice(referencePrice);
        setStatus(status);
        setCreatedAt(createdAt);
        setUpdatedAt(updatedAt);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        if (id != null && id < 1) {
            throw new IllegalArgumentException("id debe ser positivo");
        }
        this.id = id;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = Objects.requireNonNull(isbn, "isbn no puede ser null");
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = Objects.requireNonNull(title, "title no puede ser null");
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = Objects.requireNonNull(author, "author no puede ser null");
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = Objects.requireNonNull(category, "category no puede ser null");
    }

    public Integer getTotalCopies() {
        return totalCopies;
    }

    public void setTotalCopies(Integer totalCopies) {
        if (totalCopies == null || totalCopies < 0) {
            throw new IllegalArgumentException("totalCopies debe ser >= 0");
        }
        this.totalCopies = totalCopies;
    }

    public BigDecimal getReferencePrice() {
        return referencePrice;
    }

    public void setReferencePrice(BigDecimal referencePrice) {
        this.referencePrice = Objects.requireNonNull(
            referencePrice, "referencePrice no puede ser null");
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = Objects.requireNonNull(status, "status no puede ser null");
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = Objects.requireNonNull(createdAt, "createdAt no puede ser null");
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = Objects.requireNonNull(updatedAt, "updatedAt no puede ser null");
    }

    @Override
    public String toString() {
        return String.format(
            "Book{id=%d, isbn='%s', title='%s', author='%s', category='%s', totalCopies=%d, referencePrice=%s, status='%s', createdAt=%s, updatedAt=%s}",
            id, isbn, title, author, category, totalCopies, referencePrice, status, createdAt, updatedAt
        );
    }
}
