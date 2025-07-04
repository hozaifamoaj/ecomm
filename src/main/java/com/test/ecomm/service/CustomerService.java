package com.test.ecomm.service;

import com.test.ecomm.dto.WishlistItemDto;
import com.test.ecomm.exception.ResourceNotFoundException;
import com.test.ecomm.repository.CustomerRepository;
import com.test.ecomm.repository.WishlistRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerService {

    private static final Logger logger = LoggerFactory.getLogger(CustomerService.class);
    private static WishlistRepository wishlistRepository;
    private static CustomerRepository customerRepository;

    @Autowired
    public CustomerService(WishlistRepository wishlistRepository,
                           CustomerRepository customerRepository) {
        this.wishlistRepository = wishlistRepository;
        this.customerRepository = customerRepository;
    }

    public List<WishlistItemDto> getWishList(Long customerId) {
        logger.info("validating customer ID {}", customerId);

        if (customerId == null || customerId <= 0) {
            logger.error("Invalid customer ID: {}", customerId);
            throw new IllegalArgumentException("Customer ID must be positive");
        }
        if (!customerRepository.existsById(customerId)) {
            logger.error("Customer with ID {} not found", customerId);
            throw new ResourceNotFoundException("Customer with ID " + customerId + " not found");
        }
        return wishlistRepository.findWishlistItemsByCustomerId(customerId).stream()
                .map(w -> new WishlistItemDto(w.getItemId(), w.getItemName(), w.getPrice()))
                .collect(Collectors.toList());
    }

}
