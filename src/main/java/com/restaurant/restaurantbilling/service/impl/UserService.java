package com.restaurant.restaurantbilling.service.impl;
import com.restaurant.restaurantbilling.model.Users;
import com.restaurant.restaurantbilling.resository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.restaurant.restaurantbilling.jwtUtil.JwtUtil;

import java.util.HashMap;
import java.util.Map;


@Service
public class UserService {

    private final UserRepo userRepository;
    private final JwtUtil jwtUtil;


    @Autowired
    public UserService(UserRepo userRepository, JwtUtil jwtUtil) {
        this.userRepository = userRepository;
        this.jwtUtil = jwtUtil;
       // this.passwordEncoder = passwordEncoder;
    }





//    public Users registerUser(Users registerUser) {
//        // Check if a user with the same email already exists
//        Users existingUser = userRepository.findByEmail(registerUser.getEmail());
//
//        //Checking if email is already registered.
//        if(existingUser == null){
//            // Hash the password before saving it to the database
//            String hashedPassword = passwordEncoder.encode(registerUser.getPassword());
//            registerUser.setPassword(hashedPassword);
//            return userRepository.save(registerUser);
//        } else {
//            return null;
//        }
//    }
    public Users registerUser(Users registerUser) {
        System.out.println(registerUser.getEmail());
        Users existingUser = userRepository.findByEmail(registerUser.getEmail());
        //Checking if email is already registered.
        if(existingUser == null){

            // Hash the password before saving it to the database
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            String hashedPassword = passwordEncoder.encode(registerUser.getPassword());
            registerUser.setPassword(hashedPassword);
            // Hash the password before saving it to the database
            return userRepository.save(registerUser);

        } else {
            return null;
        }

    }

    public Map<String, Object> loginUser(Users loginUser) {
        Users user = userRepository.findByEmail(loginUser.getEmail());

        Map<String, Object> response = new HashMap<>();

        // Check if the user exists
        if (user != null && user.getEmail() != null && !user.getEmail().isEmpty()) {
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

            // Compare the hashed password with the plain password
            if (passwordEncoder.matches(loginUser.getPassword(), user.getPassword())) {
                System.out.println("Login successful!");

                // Remove sensitive information (password)
                user.setPassword(null);

                // Create JWT token
                Map<String, Object> claims = new HashMap<>();
                claims.put("email", user.getEmail());
                claims.put("role", "USER");
                claims.put("id", user.getId());
                String token = jwtUtil.userAuth(user.getEmail(), claims);

                // Prepare response
                response.put("status", "success");
                response.put("message", "Login successful");
                response.put("token", token);
                response.put("user", user);

                return response; // Return successful response
            } else {
                System.out.println("Invalid password!");

                // Prepare response for invalid password
                response.put("status", "error");
                response.put("message", "Invalid password");
                return response;
            }
        } else {
            System.out.println("User not found!");
            // Prepare response for user not found
            response.put("status", "error");
            response.put("message", "User not found");
            return response;
        }
    }

}
