package com.restaurant.restaurantbilling.service.impl;

import com.restaurant.restaurantbilling.model.Menu;
import com.restaurant.restaurantbilling.model.Users;
import com.restaurant.restaurantbilling.resository.MenuRepository;
import com.restaurant.restaurantbilling.resository.UserRepo;
import com.restaurant.restaurantbilling.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

import java.util.List;


@Service
public class MenuServiceImpl implements MenuService {

    private final MenuRepository menuRepository;
    private final UserRepo userRepository;

    @Autowired
    public MenuServiceImpl(MenuRepository menuRepository, UserRepo userRepository) {
        this.menuRepository = menuRepository;
        this.userRepository = userRepository;
    }

    public Menu saveMenu(Menu menu, String userId) {
        // Fetch the user from the repository using userId
        Users user = userRepository.findById(userId).orElse(null);

        if (user != null) {
            // Set the user for the menu item before saving
            menu.setUser(user);

            menu = menuRepository.save(menu);
            menu.setUser(null);
            return menu;

        } else {
            // Handle the case when the user is not found
            throw new IllegalArgumentException("User not found with userId: " + userId);
        }
    }

    @Override
    public List<Menu> getAllMenus() {
        List<Menu> responseMenuList = new ArrayList<>();
        return menuRepository.findAllMenusWithoutUser();

    }

    @Override
    public Menu getMenuById(String menuId) {
        return menuRepository.findById(menuId).orElse(null);
    }

    @Override
    public List<Menu> getMenuByUserId(String userId) {
        return menuRepository.findByUserId(userId);
    }

    @Override
    public void deleteMenu(String menuId) {
        menuRepository.deleteById(menuId);
    }
}