package com.test.ecomm.controller;

import com.test.ecomm.dto.TopSellingItemDto;
import com.test.ecomm.exception.InvalidRequestException;
import com.test.ecomm.service.SalesService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(SalesController.class)
class SalesControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private SalesService salesService;

    @Test
    void getDailySales_ReturnsTotalSales() throws Exception {
        LocalDate date = LocalDate.of(2025, 7, 2);
        when(salesService.getDailySales(date)).thenReturn(new BigDecimal("1000.00"));

        mockMvc.perform(get("/api/sales/daily/2025-07-02")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").value(1000.00));
    }

    @Test
    void getMaxSaleDay_ReturnsDate() throws Exception {
        LocalDate startDate = LocalDate.of(2025, 7, 1);
        LocalDate endDate = LocalDate.of(2025, 7, 3);
        LocalDate maxSaleDay = LocalDate.of(2025, 7, 2);
        when(salesService.getMaxSaleDay(startDate, endDate)).thenReturn(maxSaleDay);

        mockMvc.perform(get("/api/sales/max-sale-day")
                        .param("startDate", "2025-07-01")
                        .param("endDate", "2025-07-03")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").value("2025-07-02"));
    }

    @Test
    void getMaxSaleDay_InvalidDateRange_Returns400() throws Exception {
        LocalDate startDate = LocalDate.of(2025, 7, 3);
        LocalDate endDate = LocalDate.of(2025, 7, 1);
        when(salesService.getMaxSaleDay(startDate, endDate))
                .thenThrow(new InvalidRequestException("Start date must be before or equal to end date"));

        mockMvc.perform(get("/api/sales/max-sale-day")
                        .param("startDate", "2025-07-03")
                        .param("endDate", "2025-07-01")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.error").value("Start date must be before or equal to end date"));
    }

    @Test
    void getTopSellingItemsAllTime_ReturnsItems() throws Exception {
        List<TopSellingItemDto> items = List.of(
                new TopSellingItemDto("Laptop Pro", new BigDecimal("2599.98"))
        );
        when(salesService.getTopSellingItemsAllTime(5)).thenReturn(items);

        mockMvc.perform(get("/api/sales/top-items")
                        .param("limit", "5")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].itemName").value("Laptop Pro"))
                .andExpect(jsonPath("$[0].totalSales").value(2599.98));
    }

    @Test
    void getTopSellingItemsLastMonth_ReturnsItems() throws Exception {
        LocalDate startDate = LocalDate.of(2025, 7, 1);
        LocalDate endDate = LocalDate.of(2025, 7, 5);
        List<TopSellingItemDto> items = List.of(
                new TopSellingItemDto("Phone", new BigDecimal("50.0"))
        );
        when(salesService.getTopSellingItemsLastMonth(5, startDate, endDate)).thenReturn(items);

        mockMvc.perform(get("/api/sales/top-items-last-month")
                        .param("limit", "5")
                        .param("startDate", "2025-07-01")
                        .param("endDate", "2025-07-05")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].itemName").value("Phone"))
                .andExpect(jsonPath("$[0].totalSales").value(50.0));
    }
}