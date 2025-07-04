package com.test.ecomm.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.test.ecomm.dto.WishlistItemDto;
import com.test.ecomm.exception.ResourceNotFoundException;
import com.test.ecomm.service.CustomerService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CustomerController.class)
class CustomerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CustomerService customerService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void getWishlist_ReturnsWishlistItems() throws Exception {
        List<WishlistItemDto> wishlist = List.of(
                new WishlistItemDto(1L, "Laptop", new BigDecimal("999.99"))
        );
        when(customerService.getWishList(1L)).thenReturn(wishlist);

        mockMvc.perform(get("/api/customers/1/wishlist")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].itemId").value(1L))
                .andExpect(jsonPath("$[0].itemName").value("Laptop"))
                .andExpect(jsonPath("$[0].price").value(999.99));
    }

    @Test
    void getWishlist_CustomerNotFound_Returns404() throws Exception {
        when(customerService.getWishList(1L))
                .thenThrow(new ResourceNotFoundException("Customer with ID 1 not found"));

        mockMvc.perform(get("/api/customers/1/wishlist")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.error").value("Customer with ID 1 not found"));
    }
}
