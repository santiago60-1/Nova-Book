// src/main/java/com/codeup/booksnova/dao/BookDaoJdbc.java
package com.codeup.booksnova.dao;

import com.codeup.booksnova.domain.Book;
import com.codeup.booksnova.util.ConnectionFactory;

import java.sql.*;
import java.util.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * JDBC code for BookDAO.
 * This class does book CRUD in the database.
 */
public class BookDaoJdbc implements BookDAO {
    private static final String INSERT = "INSERT INTO books(isbn, title, author, category, total_copies, reference_price, status) VALUES(?,?,?,?,?,?,?)";
    private static final String UPDATE = "UPDATE books SET isbn=?, title=?, author=?, category=?, total_copies=?, reference_price=?, status=? WHERE id=?";
    private static final String DELETE = "DELETE FROM books WHERE id=?";
    private static final String FIND_BY_ID = "SELECT * FROM books WHERE id=?";
    private static final String FIND_ALL = "SELECT * FROM books";
    private static final String FIND_BY_AUTHOR = "SELECT * FROM books WHERE author=?";
    private static final String FIND_BY_CATEGORY = "SELECT * FROM books WHERE category=?";

    @Override
    public Book save(Book book) throws Exception {
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, book.getIsbn());
            ps.setString(2, book.getTitle());
            ps.setString(3, book.getAuthor());
            ps.setString(4, book.getCategory());
            ps.setInt(5, book.getTotalCopies());
            ps.setBigDecimal(6, book.getReferencePrice());
            ps.setString(7, book.getStatus());
            ps.executeUpdate();
            try (ResultSet keys = ps.getGeneratedKeys()) {
                if (keys.next()) {
                    book.setId(keys.getInt(1));
                }
            }
            return book;
        }
    }

    @Override
    public Book update(Book book) throws Exception {
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(UPDATE)) {
            ps.setString(1, book.getIsbn());
            ps.setString(2, book.getTitle());
            ps.setString(3, book.getAuthor());
            ps.setString(4, book.getCategory());
            ps.setInt(5, book.getTotalCopies());
            ps.setBigDecimal(6, book.getReferencePrice());
            ps.setString(7, book.getStatus());
            ps.setInt(8, book.getId());
            ps.executeUpdate();
            return book;
        }
    }

    @Override
    public void delete(Integer id) throws Exception {
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(DELETE)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }

    @Override
    public Optional<Book> findById(Integer id) throws Exception {
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(FIND_BY_ID)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return Optional.of(mapRow(rs));
                }
                return Optional.empty();
            }
        }
    }

    @Override
    public List<Book> findAll() throws Exception {
        List<Book> list = new ArrayList<>();
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(FIND_ALL);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                list.add(mapRow(rs));
            }
        }
        return list;
    }

    @Override
    public List<Book> findByAuthor(String author) throws Exception {
        List<Book> list = new ArrayList<>();
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(FIND_BY_AUTHOR)) {
            ps.setString(1, author);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    list.add(mapRow(rs));
                }
            }
        }
        return list;
    }

    @Override
    public List<Book> findByCategory(String category) throws Exception {
        List<Book> list = new ArrayList<>();
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(FIND_BY_CATEGORY)) {
            ps.setString(1, category);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    list.add(mapRow(rs));
                }
            }
        }
        return list;
    }

    // map one row of the result to a Book object
    private Book mapRow(ResultSet rs) throws SQLException {
        return new Book(
            rs.getInt("id"),
            rs.getString("isbn"),
            rs.getString("title"),
            rs.getString("author"),
            rs.getString("category"),
            rs.getInt("total_copies"),
            rs.getBigDecimal("reference_price"),
            rs.getString("status"),
            rs.getTimestamp("created_at").toLocalDateTime(),
            rs.getTimestamp("updated_at").toLocalDateTime()
        );
    }
}
