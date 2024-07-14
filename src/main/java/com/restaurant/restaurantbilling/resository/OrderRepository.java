package com.restaurant.restaurantbilling.resository;

import com.restaurant.restaurantbilling.model.Menu;
import com.restaurant.restaurantbilling.model.Order;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends MongoRepository<Order, String> {
    // You can define custom query methods here if needed


    @Query(value = "{}", fields = "{ 'user' : 0 }") // Exclude the 'user' field
    public List<Order> findAllordersWithoutUser() ;

    @Query(value = "{'user.id': ?0}", fields = "{ 'user' : 0 }")
    List<Order> findOrderByUserId(String userId);

}