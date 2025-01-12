package com.restaurant.restaurantbilling.controller;

import java.util.List;
import com.restaurant.restaurantbilling.model.Menu;
import com.restaurant.restaurantbilling.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
@CrossOrigin(origins = "http://localhost:4200")

//@CrossOrigin("https://resturant-billing-application.vercel.app")
@RestController
@RequestMapping("/api/menu")
public class MenuController {

    @Autowired
    private MenuService menuService;

    // Endpoint to save a menu item
    @PostMapping("saveMenu")
    public ResponseEntity<Menu> saveMenu(@RequestBody Menu menu, @RequestParam String userId) {
        Menu savedMenu = menuService.saveMenu(menu, userId);

        return new ResponseEntity<>(savedMenu, HttpStatus.CREATED);
    }

    // Endpoint to get all menu items
    @GetMapping("getAllMenu")
    public ResponseEntity<List<Menu>> getAllMenus() {
        List<Menu> menus = menuService.getAllMenus();
        return new ResponseEntity<>(menus, HttpStatus.OK);
    }

    // Endpoint to get a menu item by ID
    @GetMapping("/{menuId}")
    public ResponseEntity<Menu> getMenuById(@PathVariable String menuId) {
        Menu menu = menuService.getMenuById(menuId);
        if (menu != null) {
            return new ResponseEntity<>(menu, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Endpoint to get all menu items for a specific user
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Menu>> getMenuByUserId(@PathVariable String userId) {
        List<Menu> menus = menuService.getMenuByUserId(userId);
        return new ResponseEntity<>(menus, HttpStatus.OK);
    }

    // Endpoint to delete a menu item by ID
    @DeleteMapping("/{menuId}")
    public ResponseEntity<Void> deleteMenu(@PathVariable String menuId) {
        menuService.deleteMenu(menuId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
