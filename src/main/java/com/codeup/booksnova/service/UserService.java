// src/main/java/com/codeup/booksnova/service/UserService.java
package com.codeup.booksnova.service;

import com.codeup.booksnova.domain.User;
import java.util.List;
import java.util.Optional;

public interface UserService {
    /** Crea un nuevo usuario; lanza Exception si ya existe un nombre duplicado. */
    User create(User user) throws Exception;

    /** Busca un usuario por su ID. */
    Optional<User> findById(int id) throws Exception;

    /** Lista todos los usuarios. */
    List<User> findAll() throws Exception;

    /** Actualiza un usuario existente; lanza Exception si no existe. */
    User update(User user) throws Exception;

    /** Elimina un usuario por su ID; lanza Exception si no existe. */
    void delete(int id) throws Exception;

    /** Busca un usuario por su nombre de usuario. */
    Optional<User> findByName(String name) throws Exception;
}
