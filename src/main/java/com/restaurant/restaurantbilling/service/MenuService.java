package com.restaurant.restaurantbilling.service;

import com.restaurant.restaurantbilling.model.Menu;

import java.util.List;

public interface MenuService {

    Menu saveMenu(Menu menu, String userId);
    List<Menu> getAllMenus();
    Menu getMenuById(String menuId);
    List<Menu> getMenuByUserId(String userId);
    void deleteMenu(String menuId);
}
