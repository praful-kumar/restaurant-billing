package com.restaurant.restaurantbilling.resository;

import com.restaurant.restaurantbilling.model.Sale;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface SaleRepository extends MongoRepository<Sale, String> {
    // You can add custom query methods if needed
}
