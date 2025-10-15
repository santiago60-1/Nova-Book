// src/main/java/com/codeup/booksnova/service/BookService.java
package com.codeup.booksnova.service;

import com.codeup.booksnova.domain.Book;
import java.util.List;
import java.util.Optional;

public interface BookService {
    Book create(Book book) throws Exception;
    Optional<Book> findById(int bookId) throws Exception;
    List<Book> findAll() throws Exception;
    Book update(Book book) throws Exception;
    void delete(int bookId) throws Exception;
}
