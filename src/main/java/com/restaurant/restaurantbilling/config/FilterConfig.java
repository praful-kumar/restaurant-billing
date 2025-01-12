package com.restaurant.restaurantbilling.config;
import com.restaurant.restaurantbilling.jwtUtil.JwtAuthenticationFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterConfig {

     private final JwtAuthenticationFilter jwtAuthenticationFilter;

    // Constructor injection
    public FilterConfig(JwtAuthenticationFilter jwtAuthenticationFilter) {
        this.jwtAuthenticationFilter = jwtAuthenticationFilter;
    }

    // Register the filter for the desired URL patterns
    @Bean
    public FilterRegistrationBean<JwtAuthenticationFilter> loggingFilter() {
        FilterRegistrationBean<JwtAuthenticationFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(jwtAuthenticationFilter);
        registrationBean.addUrlPatterns("/api/*");  // Apply to all API endpoints
        return registrationBean;
    }
}

