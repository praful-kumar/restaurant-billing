package com.restaurant.restaurantbilling.jwtUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@Component
@WebFilter("/*") // Intercept all requests
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private JwtUtil jwtUtil;

    @Autowired
    public JwtAuthenticationFilter(JwtUtil jwtUtil) {
         this.jwtUtil= jwtUtil;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        // Extract token from Authorization header
        String token = request.getHeader("Authorization");

        // Get the request URI
        String requestURI = request.getRequestURI();

        // Define a list of public endpoints
        List<String> publicEndpoints = Arrays.asList(
                "/api/users/signin",
                "/api/users/register",
                "/api/users/forgot-password",
                "/api/public/",
                "/health"
        );

        if (token != null && token.startsWith("Bearer ")) {
            token = token.substring(7); // Remove "Bearer " prefix

            try {
                String username = jwtUtil.extractUsername(token);

                // Check if the token is valid
                if (jwtUtil.validateToken(token, username)) {
                    // Proceed with the request if token is valid
                    Authentication authentication = jwtUtil.getAuthentication(token);
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                    filterChain.doFilter(request, response);

                } else {
                    // If invalid token, respond with Unauthorized status
                    response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                    response.getWriter().write("Invalid or expired JWT token");
                }
            } catch (Exception e) {
                // Handle token parsing and validation errors
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                response.getWriter().write("Invalid JWT token");
            }
        } else if(publicEndpoints.stream().anyMatch(requestURI::startsWith)) {
            filterChain.doFilter(request, response); // Proceed without token validation
        }
    }

//    @Override
//    public void init(FilterConfig filterConfig) throws ServletException {
//        // Initialization logic if needed
//    }

    @Override
    public void destroy() {
        // Cleanup logic if needed
        System.out.println("Filter is being destroyed. Cleanup tasks are being performed.");
    }
}

