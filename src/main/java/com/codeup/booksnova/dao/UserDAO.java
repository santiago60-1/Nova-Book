/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.codeup.booksnova.dao;

import com.codeup.booksnova.domain.User;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author Santiago Ortega
 */
public interface UserDAO {
    User save(User user) throws Exception;
    User update(User user) throws Exception;
    void delete(Integer id) throws Exception;
    Optional<User> finById(Integer id) throws Exception;
    List<User> findAll() throws Exception;
}