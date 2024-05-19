package com.restaurant.restaurantbilling.service.impl;

import com.restaurant.restaurantbilling.model.Configuration;
import com.restaurant.restaurantbilling.resository.ConfigurationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ConfigurationService {

    private final ConfigurationRepository configurationRepository;

    @Autowired
    public ConfigurationService(ConfigurationRepository configurationRepository) {
        this.configurationRepository = configurationRepository;
    }

    public List<Configuration> getConfigurationsForUser(String userId) {
        return configurationRepository.findByUserId(userId);
    }

    // You can add more methods to the ConfigurationService class as needed
}