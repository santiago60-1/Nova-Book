// src/main/java/com/codeup/booksnova/dao/LoanDAO.java
package com.codeup.booksnova.dao;

import com.codeup.booksnova.domain.Loan;
import java.sql.Connection;
import java.util.List;
import java.util.Optional;

/**
 * CRUD interface for Loan plus overdue query.
 */
public interface LoanDAO {
    // Create
    Loan save(Loan loan, Connection conn) throws Exception;

    // Read by id
    Optional<Loan> findById(Integer id) throws Exception;

    // Read all
    List<Loan> findAll() throws Exception;

    // Read overdue
    List<Loan> findOverdue() throws Exception;

    // Update
    Loan update(Loan loan, Connection conn) throws Exception;

    // Delete
    void delete(Integer id) throws Exception;

    public List<Loan> findByPartnerName(String partnerName);
}