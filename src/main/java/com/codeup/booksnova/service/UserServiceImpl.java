// src/main/java/com/codeup/booksnova/service/UserServiceImpl.java
package com.codeup.booksnova.service;

import com.codeup.booksnova.dao.UserDAO;
import com.codeup.booksnova.dao.UserDaoJdbc;
import com.codeup.booksnova.domain.User;

import java.util.List;
import java.util.Optional;

public class UserServiceImpl implements UserService {
    private final UserDAO userDao = new UserDaoJdbc();

    @Override
    public User create(User user) throws Exception {
        // Regla de negocio: nombre único
        if (userDao.finById(user.getId()).isPresent()) {
            throw new Exception("El nombre de usuario ya está en uso.");
        }
        return userDao.save(user);
    }

    @Override
    public Optional<User> findById(int id) throws Exception {
        return userDao.finById(id);
    }

    @Override
    public List<User> findAll() throws Exception {
        return userDao.findAll();
    }

    @Override
    public User update(User user) throws Exception {
        // Validar que exista antes de actualizar
        if (userDao.finById(user.getId()).isEmpty()) {
            throw new Exception("Usuario con ID " + user.getId() + " no existe.");
        }
        return userDao.update(user);
    }

    @Override
    public void delete(int id) throws Exception {
        // Validar que exista antes de eliminar
        if (userDao.finById(id).isEmpty()) {
            throw new Exception("Usuario con ID " + id + " no existe.");
        }
        userDao.delete(id);
    }

    @Override
    public Optional<User> findByName(String name) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
