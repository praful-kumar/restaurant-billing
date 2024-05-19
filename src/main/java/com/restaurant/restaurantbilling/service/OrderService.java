package com.restaurant.restaurantbilling.service;

import com.restaurant.restaurantbilling.model.Menu;
import com.restaurant.restaurantbilling.model.Order;

import java.util.List;

public interface OrderService {

    List<Order> getOrders();
}
