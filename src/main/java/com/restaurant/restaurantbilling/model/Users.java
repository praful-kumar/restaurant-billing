package com.restaurant.restaurantbilling.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "users")
//@Data
public class Users {
    @Id
    private String id;
    private String email;
    private String password; // Note: In production, consider using hashed passwords.

    @DBRef
    private List<Sale> sells;

    @DBRef
    private List<Table> tables;

    @DBRef
    private List<Menu> menus;
    // getters and setters


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}