package com.restaurant.restaurantbilling.controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import com.restaurant.restaurantbilling.model.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.restaurant.restaurantbilling.service.impl.UserService;

import java.util.HashMap;
import java.util.Map;


//@CrossOrigin("https://resturant-billing-application.vercel.app")
@RestController
@RequestMapping("/api/users")
public class UserController {


    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<Map<String, Object>> registerUser(@RequestBody Users user) {
        Users registeredUser = userService.registerUser(user);
        Map<String, Object> responseBody = new HashMap<>();
        if (registeredUser != null) {
            responseBody.put("Id",registeredUser.getId());
            responseBody.put("email",registeredUser.getEmail());
            return ResponseEntity.ok(responseBody);
        }else {
            responseBody.put("error", "Email already taken. Please use another email to register.");
            return ResponseEntity.status(401).body(responseBody);
        }
    }

    @PostMapping("/signin")
    public ResponseEntity<Map<String, Object>> loginUser(@RequestBody Users user) {
        Map<String, Object> authenticatedUser = userService.loginUser(user);

        if (authenticatedUser.get("status") != "error") {
           // responseBody.put("Token",token);
            return ResponseEntity.ok(authenticatedUser);
        } else {
            return ResponseEntity.status(401).body(authenticatedUser);
        }
    }

}
