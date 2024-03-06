package com.restaurant.restaurantbilling.controller;

import com.restaurant.restaurantbilling.model.Sale;
import com.restaurant.restaurantbilling.service.SalesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/sales")
public class SalesController {

    private final SalesService salesService;

    @Autowired
    public SalesController(SalesService saleService) {
        this.salesService = saleService;
    }

    @PostMapping("/sales")
    public Sale saveSale(@RequestBody Sale sale) {

        return salesService.saveSale(sale);
    }
    @GetMapping("/sales")
    public List<Sale> getAllSales() {
        return salesService.getAllSales();
    }
}
