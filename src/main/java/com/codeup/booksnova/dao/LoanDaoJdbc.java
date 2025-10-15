// src/main/java/com/codeup/booksnova/dao/LoanDaoJdbc.java
package com.codeup.booksnova.dao;

import com.codeup.booksnova.domain.Loan;
import com.codeup.booksnova.util.ConnectionFactory;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class LoanDaoJdbc implements LoanDAO {
    private static final String INSERT = "INSERT INTO loans(id_book,id_partners,issue_loan,return_date,fine,is_devuelto) VALUES(?,?,?,?,?,?)";
    private static final String UPDATE = "UPDATE loans SET return_date=?,fine=?,is_devuelto=? WHERE id=?";
    private static final String DELETE = "DELETE FROM loans WHERE id=?";
    private static final String FIND_BY_ID = "SELECT * FROM loans WHERE id=?";
    private static final String FIND_ALL = "SELECT * FROM loans";
    private static final String FIND_OVERDUE = "SELECT * FROM loans WHERE return_date < CURRENT_DATE() AND is_devuelto='NO'";

    @Override
    public Loan save(Loan loan, Connection conn) throws SQLException {
        try (PreparedStatement ps = conn.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS)) {
            ps.setInt(1, loan.getBook().getId());
            ps.setInt(2, loan.getPartner().getId());
            ps.setDate(3, Date.valueOf(loan.getIssueLoan()));
            ps.setDate(4, Date.valueOf(loan.getReturnDate()));
            ps.setBigDecimal(5, loan.getFine());
            ps.setString(6, loan.getDevuelto());
            ps.executeUpdate();
            try (ResultSet keys = ps.getGeneratedKeys()) {
                if (keys.next()) loan.setId(keys.getInt(1));
            }
            return loan;
        }
    }

    @Override
    public Optional<Loan> findById(Integer id) throws Exception {
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(FIND_BY_ID)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) return Optional.of(mapRow(rs));
                return Optional.empty();
            }
        }
    }

    @Override
    public List<Loan> findAll() throws Exception {
        List<Loan> list = new ArrayList<>();
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(FIND_ALL);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) list.add(mapRow(rs));
        }
        return list;
    }

    @Override
    public List<Loan> findOverdue() throws Exception {
        List<Loan> list = new ArrayList<>();
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(FIND_OVERDUE);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) list.add(mapRow(rs));
        }
        return list;
    }

    @Override
    public Loan update(Loan loan, Connection conn) throws SQLException {
        try (PreparedStatement ps = conn.prepareStatement(UPDATE)) {
            ps.setDate(1, Date.valueOf(loan.getReturnDate()));
            ps.setBigDecimal(2, loan.getFine());
            ps.setString(3, loan.getDevuelto());
            ps.setInt(4, loan.getId());
            ps.executeUpdate();
            return loan;
        }
    }

    @Override
    public void delete(Integer id) throws SQLException, Exception {
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(DELETE)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }

    private Loan mapRow(ResultSet rs) throws SQLException {
        return new Loan(
            rs.getInt("id"),
            // asume que ya tienes método para cargar Book/Partner por id
            /* Book */ null,
            /* Partner */ null,
            rs.getDate("issue_loan").toLocalDate(),
            rs.getDate("return_date").toLocalDate(),
            rs.getBigDecimal("fine"),
            rs.getString("is_devuelto"),
            rs.getTimestamp("created_at").toLocalDateTime(),
            rs.getTimestamp("updated_at").toLocalDateTime()
        );
    }

    @Override
    public List<Loan> findByPartnerName(String partnerName) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}