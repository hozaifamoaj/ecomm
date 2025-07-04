package com.test.ecomm.repository;

import com.test.ecomm.dto.TopSellingItemDto;
import com.test.ecomm.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<OrderEntity, Long> {

    @Query("SELECT COALESCE(SUM(o.totalAmount), 0) FROM OrderEntity o WHERE o.orderDate >= :startOfDay AND o.orderDate < :endOfDay")
    Optional<BigDecimal> findTotalSalesByDate(LocalDateTime startOfDay, LocalDateTime endOfDay);

    @Query("SELECT DATE(o.orderDate) FROM OrderEntity o " +
            "WHERE o.orderDate BETWEEN :startDate AND :endDate " +
            "GROUP BY DATE(o.orderDate) " +
            "ORDER BY SUM(o.totalAmount) DESC LIMIT 1")
    Optional<LocalDate> findMaxSaleDay(LocalDateTime startDate, LocalDateTime endDate);

    @Query("SELECT new com.test.ecomm.dto.TopSellingItemDto(i.name, SUM(oi.quantity * i.price)) " +
            "FROM OrderItemEntity oi JOIN oi.item i " +
            "GROUP BY i.id, i.name " +
            "ORDER BY SUM(oi.quantity * i.price) DESC LIMIT :limit")
    List<TopSellingItemDto> findTopSellingItemsAllTime(int limit);

    @Query("SELECT new com.test.ecomm.dto.TopSellingItemDto(i.name, SUM(oi.quantity)) " +
            "FROM OrderItemEntity oi JOIN oi.item i " +
            "JOIN oi.order o " +
            "WHERE o.orderDate BETWEEN :startDate AND :endDate " +
            "GROUP BY i.id, i.name " +
            "ORDER BY SUM(oi.quantity) DESC LIMIT :limit")
    List<TopSellingItemDto> findTopSellingItemsByDateRange(LocalDateTime startDate, LocalDateTime endDate, int limit);
}

