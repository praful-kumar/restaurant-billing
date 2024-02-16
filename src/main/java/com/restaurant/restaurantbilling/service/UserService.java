package com.restaurant.restaurantbilling.service;
import com.restaurant.restaurantbilling.model.Users;
import com.restaurant.restaurantbilling.resository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserService {

    private final UserRepo userRepository;
    //private final BCryptPasswordEncoder passwordEncoder;
    @Autowired
    public UserService(UserRepo userRepository) {
        this.userRepository = userRepository;
       // this.passwordEncoder = passwordEncoder;
    }

    public Users registerUser(Users user) {

        // Hash the password before saving it to the database
        user.setPassword(user.getPassword());
        return userRepository.save(user);
    }
    public Users loginUser(Users logeruser) {
        Users user = userRepository.findByUsername(logeruser.getUsername());

        if (user != null ) {
            return user;
        } else {
            return null;
        }
    }
}
