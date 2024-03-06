package com.restaurant.restaurantbilling.resository;

import com.restaurant.restaurantbilling.model.Configuration;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ConfigurationRepository extends MongoRepository<Configuration, String> {
    List<Configuration> findByUserId(String userId);
    List<Configuration> findByUserEmail(String userEmail);
}
