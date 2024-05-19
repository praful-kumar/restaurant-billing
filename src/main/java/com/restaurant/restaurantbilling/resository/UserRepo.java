package com.restaurant.restaurantbilling.resository;


import com.restaurant.restaurantbilling.model.Users;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepo extends MongoRepository<Users, String> {
    Users findByEmail(String email);
//    @Query(value = "{ '_id' : ?0 }", fields = "{ 'email' : 1, '_id' : 0 }")
//   String findEmailById(String id);
    Optional<Users> findById(String userId);
}

