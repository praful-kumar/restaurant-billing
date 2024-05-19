package com.restaurant.restaurantbilling.service.impl;
import com.restaurant.restaurantbilling.model.Users;
import com.restaurant.restaurantbilling.resository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.Objects;


@Service
public class UserService {

    private final UserRepo userRepository;


    @Autowired
    public UserService(UserRepo userRepository) {
        this.userRepository = userRepository;
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
            registerUser.setPassword(registerUser.getPassword());
            return userRepository.save(registerUser);

        } else {
            return null;
        }

    }
    public Users loginUser(Users logeruser) {
        Users user = userRepository.findByEmail(logeruser.getEmail());
        // user != null  && (Objects.equals(user.getPassword(), logeruser.getPassword()))
        if (user != null && user.getEmail() != null && !user.getEmail().isEmpty()) {
            System.out.println(user.getPassword());

            return user;
        } else {
            return null;
        }
    }

//    public Users loginUser(Users logeruser) {
//        // Retrieve the user from the database by email
//        Users user = userRepository.findByEmail(logeruser.getEmail());
//
//        // Check if user exists and compare passwords
//        if (user != null && passwordEncoder.matches(logeruser.getPassword(), user.getPassword())) {
//            // Passwords match, return the user
//            return user;
//        } else {
//            // Either user not found or passwords don't match, return null
//            return null;
//        }
//    }
}
