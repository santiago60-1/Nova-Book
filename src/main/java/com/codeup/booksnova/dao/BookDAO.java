// src/main/java/com/codeup/booksnova/dao/BookDAO.java
package com.codeup.booksnova.dao;

import com.codeup.booksnova.domain.Book;
import java.util.List;
import java.util.Optional;

/**
 * Defines CRUD and query operations for Book entities.
 */
public interface BookDAO {
    /**
     * Insert a new book into the database.
     * @param book the book to save
     * @return the saved book with generated id
     */
    Book save(Book book) throws Exception;

    /**
     * Update an existing book in the database.
     * @param book the book with updated fields
     * @return the updated book
     */
    Book update(Book book) throws Exception;

    /**
     * Delete a book by its id.
     * @param id the id of the book to delete
     */
    void delete(Integer id) throws Exception;

    /**
     * Find a book by its id.
     * @param id the book id
     * @return an Optional containing the book if found
     */
    Optional<Book> findById(Integer id) throws Exception;

    /**
     * Retrieve all books.
     * @return list of all books
     */
    List<Book> findAll() throws Exception;

    /**
     * Find books by a given author.
     * @param author the author name
     * @return list of books by that author
     */
    List<Book> findByAuthor(String author) throws Exception;

    /**
     * Find books in a given category.
     * @param category the category name
     * @return list of books in that category
     */
    List<Book> findByCategory(String category) throws Exception;
}
