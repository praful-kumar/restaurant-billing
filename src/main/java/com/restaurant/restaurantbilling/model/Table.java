package com.restaurant.restaurantbilling.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
@Document(collection = "tables")
public class Table {

    @Id
    private String id;

    @DBRef
    private Sale sale;

    @DBRef
    private List<Menu> menus;

    @DBRef
    private Users user;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Sale getSale() {
        return sale;
    }

    public void setSale(Sale sale) {
        this.sale = sale;
    }

    public List<Menu> getMenus() {
        return menus;
    }

    public void setMenus(List<Menu> menus) {
        this.menus = menus;
    }

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }
}
