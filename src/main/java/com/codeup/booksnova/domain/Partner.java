// src/main/java/com/codeup/booksnova/domain/Partner.java
package com.codeup.booksnova.domain;

import java.time.LocalDateTime;
import java.util.Objects;

public class Partner {
    private Integer id;
    private String name;
    private String status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    
             /**
     * Constructor with all arguments.
     * It builds a Book object with the given values.
     * We call each setter to check the data is valid.
     *
     * @param id            user id
     * @param name          name user
     * @param status        status of user
     * @param createdAt     creation time
     * @param updatedAt     update time
     */

    public Partner(Integer id, String name, String status, LocalDateTime createdAt, LocalDateTime updatedAt) {
        setId(id);
        setName(name);
        setStatus(status);
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = Objects.requireNonNull(status, "status no puede ser null");
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
            "Partner{id=%d, name='%s', status='%s', createdAt=%s, updateAt=%s}",
            id, name, status, createdAt, updatedAt
        );
    }
}