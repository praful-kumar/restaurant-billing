package com.restaurant.restaurantbilling.resository;

import com.restaurant.restaurantbilling.model.Menu;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuRepository extends MongoRepository<Menu, String> {
    @Query(value = "{'user.id': ?0}", fields = "{ 'user' : 0 }")
    List<Menu> findByUserId(String userId);

    @Query(value = "{}", fields = "{ 'user' : 0 }") // Exclude the 'user' field
    List<Menu> findAllMenusWithoutUser();

}
