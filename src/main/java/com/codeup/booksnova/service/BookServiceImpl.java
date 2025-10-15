// src/main/java/com/codeup/booksnova/service/BookServiceImpl.java
package com.codeup.booksnova.service;

import com.codeup.booksnova.dao.BookDAO;
import com.codeup.booksnova.dao.BookDaoJdbc;
import com.codeup.booksnova.domain.Book;

import java.util.List;
import java.util.Optional;

public class BookServiceImpl implements BookService {
    private final BookDAO bookDao = new BookDaoJdbc();

    @Override
    public Book create(Book book) throws Exception {
        // you could add extra validation here
        return bookDao.save(book);
    }

    @Override
    public Optional<Book> findById(int bookId) throws Exception {
        return bookDao.findById(bookId);
    }

    @Override
    public List<Book> findAll() throws Exception {
        return bookDao.findAll();
    }

    @Override
    public Book update(Book book) throws Exception {
        // validate e.g., non-negative copies, non-empty title
        return bookDao.update(book);
    }

    @Override
    public void delete(int bookId) throws Exception {
        bookDao.delete(bookId);
    }
}