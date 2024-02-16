package com.restaurant.restaurantbilling.service;

import com.restaurant.restaurantbilling.model.Users;
import com.restaurant.restaurantbilling.resository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class UserService {

    private final UserRepo userRepository;

    private  BCryptPasswordEncoder passwordEncoder;
    @Bean
    public PasswordEncoder passwordEncoder()
    {
        return new BCryptPasswordEncoder();
    }
    @Autowired
    public UserService(UserRepo userRepository) {
        this.userRepository = userRepository;

    }

    public Users registerUser(Users user) {
        // Hash the password before saving it to the database
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    public Users loginUser(String username, String password) {
        Users user = userRepository.findByUsername(username);

        if (user != null && passwordEncoder.matches(password, user.getPassword())) {
            return user;
        } else {
            return null;
        }
    }
}
