package com.restaurant.restaurantbilling.controller;

import com.restaurant.restaurantbilling.model.Menu;
import com.restaurant.restaurantbilling.model.Order;
import com.restaurant.restaurantbilling.model.Sale;
import com.restaurant.restaurantbilling.service.impl.OrderServiceImpl;
import com.restaurant.restaurantbilling.service.impl.SalesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


//@CrossOrigin("https://resturant-billing-application.vercel.app")
@RestController
@RequestMapping("/api/orders")
public class SalesController {

    private final SalesService salesService;
    private final OrderServiceImpl orderService;
    @Autowired
    public SalesController(SalesService saleService, OrderServiceImpl orderService) {
        this.salesService = saleService;
        this.orderService = orderService;
    }


    @PostMapping("/sales")
    public Sale saveSale(@RequestBody Sale sale) {

        return salesService.saveSale(sale);
    }
    @GetMapping("/sales")
    public List<Sale> getAllSales() {
        return salesService.getAllSales();
    }
    @PostMapping("/storeOrders")
    public ResponseEntity<Map<String, Object>> saveOrders(@RequestBody Order order, @RequestParam String userId) {
        Order savedOrder = orderService.saveOrder(order, userId);
        Map<String, Object> responseBody = new HashMap<>();
        if (savedOrder != null) {
            responseBody.put("order_Id",savedOrder.getId());
            responseBody.put("time",savedOrder.getCurrentDateAndTime());
            return ResponseEntity.ok(responseBody);
        }else {
            responseBody.put("error", "Something went wrong, Contact Admin");
            return ResponseEntity.status(401).body(responseBody);
        }
    }

    @GetMapping("/getOrders")
    public  ResponseEntity<List<Order>> getOrders(){
        List<Order> orders  = orderService.getOrders();
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }

    @GetMapping("/getOrders/user/{userId}")
    public  ResponseEntity<List<Order>> getOrders( @PathVariable String userId) {
        List<Order> orders = orderService.getUserOrders(userId);
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }

}
