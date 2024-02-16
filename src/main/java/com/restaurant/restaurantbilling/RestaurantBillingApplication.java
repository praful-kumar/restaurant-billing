package com.restaurant.restaurantbilling;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;


@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class RestaurantBillingApplication {

	public static void main(String[] args) {
		SpringApplication.run(RestaurantBillingApplication.class, args);
	}

}
