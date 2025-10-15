// src/main/java/com/codeup/booksnova/dao/PartnerDaoJdbc.java
package com.codeup.booksnova.dao;

import com.codeup.booksnova.domain.Partner;
import com.codeup.booksnova.util.ConnectionFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * JDBC implementation of PartnerDAO.
 */
public class PartnerDaoJdbc implements PartnerDAO {
    private static final String INSERT     = "INSERT INTO partners(name,status) VALUES(?,?)";
    private static final String FIND_BY_ID = "SELECT * FROM partners WHERE id=?";
    private static final String FIND_ALL   = "SELECT * FROM partners";
    private static final String UPDATE     = "UPDATE partners SET name=?,status=? WHERE id=?";
    private static final String DELETE     = "DELETE FROM partners WHERE id=?";

    @Override
    public Partner save(Partner partner) throws Exception {
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, partner.getName());
            ps.setString(2, partner.getStatus());
            ps.executeUpdate();

            try (ResultSet keys = ps.getGeneratedKeys()) {
                if (keys.next()) {
                    partner.setId(keys.getInt(1));
                }
            }
            return partner;
        }
    }

    @Override
    public Optional<Partner> findById(Integer id) throws Exception {
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
    public List<Partner> findAll() throws Exception {
        List<Partner> list = new ArrayList<>();
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
    public Partner update(Partner partner) throws Exception {
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(UPDATE)) {
            ps.setString(1, partner.getName());
            ps.setString(2, partner.getStatus());
            ps.setInt(3, partner.getId());
            ps.executeUpdate();
            return partner;
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

    // Map a ResultSet row to a Partner object
    private Partner mapRow(ResultSet rs) throws SQLException {
        return new Partner(
            rs.getInt("id"),
            rs.getString("name"),
            rs.getString("status"),
            rs.getTimestamp("created_at").toLocalDateTime(),
            rs.getTimestamp("updated_at").toLocalDateTime()
        );
    }
}
