package com.restaurant.restaurantbilling.service.impl;

import com.restaurant.restaurantbilling.model.Menu;
import com.restaurant.restaurantbilling.model.Order;
import com.restaurant.restaurantbilling.model.Users;
import com.restaurant.restaurantbilling.resository.OrderRepository;
import com.restaurant.restaurantbilling.resository.UserRepo;
import com.restaurant.restaurantbilling.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final UserRepo userRepository;
    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository, UserRepo userRepository) {
        this.orderRepository = orderRepository;
        this.userRepository = userRepository;
    }

    public Order saveOrder(Order order, String userId) {
        Users user = userRepository.findById(userId).orElse(null);
        order.setUser(user);
        orderRepository.save(order);
        return order != null ? order : null;

    }

//    @Override
//    public List<Menu> getAllMenus() {
//        List<Menu> responseMenuList = new ArrayList<>();
//        return menuRepository.findAllMenusWithoutUser();
//
//    }

    @Override
    public List<Order> getOrders() {
        return orderRepository.findAllordersWithoutUser();
    }
}