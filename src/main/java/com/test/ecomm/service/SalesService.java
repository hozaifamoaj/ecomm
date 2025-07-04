package com.test.ecomm.service;


import com.test.ecomm.dto.TopSellingItemDto;
import com.test.ecomm.exception.InvalidRequestException;
import com.test.ecomm.repository.OrderRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class SalesService {

    private static final Logger logger = LoggerFactory.getLogger(SalesService.class);
    private final OrderRepository orderRepository;

    @Autowired
    public SalesService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public BigDecimal getDailySales(LocalDate date) {
        logger.info("Fetching total sales for date: {}", date);
        LocalDateTime startOfDay = date.atStartOfDay();
        LocalDateTime endOfDay = date.atTime(23, 59, 59, 999999999);
        return orderRepository.findTotalSalesByDate(startOfDay, endOfDay)
                .orElse(BigDecimal.ZERO);
    }

    public LocalDate getMaxSaleDay(LocalDate startDate, LocalDate endDate) {
        logger.info("Fetching max sale day between {} and {}", startDate, endDate);
        if (startDate.isAfter(endDate)) {
            logger.error("Start date {} is after end date {}", startDate, endDate);
            throw new InvalidRequestException("Start date must be before or equal to end date");
        }
        LocalDateTime startDateTime = startDate.atStartOfDay();
        LocalDateTime endDateTime = endDate.atTime(23, 59, 59, 999999999);
        return orderRepository.findMaxSaleDay(startDateTime, endDateTime)
                .orElseThrow(() -> {
                    logger.error("No sales data found between {} and {}", startDate, endDate);
                    return new InvalidRequestException("No sales data found for the given date range");
                });
    }

    public List<TopSellingItemDto> getTopSellingItemsAllTime(int limit) {
        logger.info("Fetching top {} selling items of all time", limit);
        if (limit <= 0) {
            logger.error("Limit {} must be positive", limit);
            throw new IllegalArgumentException("Limit must be positive");
        }
        return orderRepository.findTopSellingItemsAllTime(limit);
    }

    public List<TopSellingItemDto> getTopSellingItemsLastMonth(int limit, LocalDate startDate, LocalDate endDate) {
        logger.info("Fetching top {} selling items between {} and {}", limit, startDate, endDate);
        if (limit <= 0) {
            logger.error("Limit {} must be positive", limit);
            throw new IllegalArgumentException("Limit must be positive");
        }
        if (startDate.isAfter(endDate)) {
            logger.error("Start date {} is after end date {}", startDate, endDate);
            throw new InvalidRequestException("Start date must be before or equal to end date");
        }
        LocalDateTime startDateTime = startDate.atStartOfDay();
        LocalDateTime endDateTime = endDate.atTime(23, 59, 59, 999999999);
        return orderRepository.findTopSellingItemsByDateRange(startDateTime, endDateTime, limit);
    }
}
