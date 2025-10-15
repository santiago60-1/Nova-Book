// src/main/java/com/codeup/booksnova/domain/User.java
package com.codeup.booksnova.domain;

import java.time.LocalDateTime;
import java.util.Objects;

public class User {
    private Integer id;
    private String name;
    private String role;
    private String status;
    private String password;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    
         /**
     * Constructor with all arguments.
     * It builds a Book object with the given values.
     * We call each setter to check the data is valid.
     *
     * @param id            user id
     * @param name          name user
     * @param role          role user
     * @param status        status of user
     * @param password      password of user
     * @param createdAt     creation time
     * @param updatedAt     update time
     */

    public User(Integer id, String name, String role, String status, String password,LocalDateTime createdAt, LocalDateTime updatedAt) {
        setId(id);
        setName(name);
        setRole(role);
        setStatus(status);
        setPassword(password);
        setCreatedAt(createdAt);
        setUpdatedAt(updatedAt);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        if (id != null && id < 1) {
            throw new IllegalArgumentException("id debe ser positivo");
        }
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = Objects.requireNonNull(name, "name no puede ser null");
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = Objects.requireNonNull(role, "role no puede ser null");
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = Objects.requireNonNull(status, "status no puede ser null");
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = Objects.requireNonNull(password, "password no puede ser null");
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = Objects.requireNonNull(createdAt, "createdAt no puede ser null");
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updateAt) {
        this.updatedAt = Objects.requireNonNull(updateAt, "updateAt no puede ser null");
    }

    @Override
    public String toString() {
        return String.format(
            "User{id=%d, name='%s', role='%s', status='%s', createdAt=%s, updateAt=%s}",
            id, name, role, status, createdAt, updatedAt
        );
    }
}
