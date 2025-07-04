package com.test.ecomm.controller;


import com.test.ecomm.dto.WishlistItemDto;
import com.test.ecomm.service.CustomerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {

    private static final Logger logger = LoggerFactory.getLogger(CustomerController.class);

    private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @Operation(summary = "Get customer wishlist", description = "Returns the wishlist for a given customer ID")
    @ApiResponse(responseCode = "200", description = "Wishlist retrieved successfully")
    @ApiResponse(responseCode = "400", description = "Customer ID must be positive")
    @ApiResponse(responseCode = "404", description = "Customer not found")
    @GetMapping("/{customerId}/wishlist")
    public ResponseEntity<List<WishlistItemDto>> getWishlist(@PathVariable Long customerId) {
        logger.info("Fetching wishlist for customer ID: {}", customerId);
        List<WishlistItemDto> wishlist = customerService.getWishList(customerId);
        logger.info("Retrieved {} wishlist items for customer ID: {}", wishlist.size(), customerId);
        return ResponseEntity.ok(wishlist);
    }
}
