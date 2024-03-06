package com.restaurant.restaurantbilling.service;
import com.restaurant.restaurantbilling.model.Users;
import com.restaurant.restaurantbilling.resository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;


@Service
public class UserService {

    private final UserRepo userRepository;

    //private final BCryptPasswordEncoder passwordEncoder;
    @Autowired
    public UserService(UserRepo userRepository) {
        this.userRepository = userRepository;
       // this.passwordEncoder = passwordEncoder;
    }

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

        if (user != null  && (Objects.equals(user.getPassword(), logeruser.getPassword())) ) {
//            System.out.println(user.getPassword());
            return user;
        } else {
            return null;
        }
    }
}
