package com.test.ecomm.controller;

import com.test.ecomm.dto.TopSellingItemDto;
import com.test.ecomm.service.SalesService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/sales")
public class SalesController {

    private static final Logger logger = LoggerFactory.getLogger(SalesController.class);
    private final SalesService salesService;

    @Autowired
    public SalesController(SalesService salesService) {
        this.salesService = salesService;
    }

    @Operation(summary = "Get total sales for a specific date", description = "Returns the total sales amount for a given date")
    @ApiResponse(responseCode = "200", description = "Total sales retrieved successfully")
    @GetMapping("/daily/{date}")
    public ResponseEntity<BigDecimal> getDailySales(@PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        return ResponseEntity.ok(salesService.getDailySales(date));
    }

    @Operation(summary = "Get the date with maximum sales", description = "Returns the date with the highest total sales within a date range")
    @ApiResponse(responseCode = "200", description = "Max sale date retrieved successfully")
    @ApiResponse(responseCode = "400", description = "Invalid date range")
    @GetMapping("/max-sale-day")
    public ResponseEntity<LocalDate> getMaxSaleDay(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        return ResponseEntity.ok(salesService.getMaxSaleDay(startDate, endDate));
    }

    @Operation(summary = "Get top-selling items of all time", description = "Returns the top N items by total sales amount")
    @ApiResponse(responseCode = "200", description = "Top-selling items retrieved successfully")
    @ApiResponse(responseCode = "400", description = "Invalid limit parameter")
    @GetMapping("/top-items")
    public ResponseEntity<List<TopSellingItemDto>> getTopSellingItemsAllTime(@RequestParam(defaultValue = "5") int limit) {
        return ResponseEntity.ok(salesService.getTopSellingItemsAllTime(limit));
    }

    @Operation(summary = "Get top-selling items for last month", description = "Returns the top N items by quantity sold within a date range")
    @ApiResponse(responseCode = "200", description = "Top-selling items retrieved successfully")
    @ApiResponse(responseCode = "400", description = "Invalid limit or date range")
    @GetMapping("/top-items-last-month")
    public ResponseEntity<List<TopSellingItemDto>> getTopSellingItemsLastMonth(
            @RequestParam(defaultValue = "5") int limit,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        return ResponseEntity.ok(salesService.getTopSellingItemsLastMonth(limit, startDate, endDate));
    }
}
