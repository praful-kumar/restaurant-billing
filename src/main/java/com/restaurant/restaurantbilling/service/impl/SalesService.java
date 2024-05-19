package com.restaurant.restaurantbilling.service.impl;
import com.restaurant.restaurantbilling.model.Sale;
import com.restaurant.restaurantbilling.resository.SaleRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SalesService {

    private final SaleRepository saleRepository;

    @Autowired
    public SalesService(SaleRepository saleRepository) {
        this.saleRepository = saleRepository;
    }

    public List<Sale> getAllSales() {
        return saleRepository.findAll();
    }
    public Sale saveSale(Sale sale) {
        return saleRepository.save(sale);
    }

    // You can add more methods to the SaleService class as needed
}