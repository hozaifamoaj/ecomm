package com.test.ecomm.entity;

import com.test.ecomm.common.BaseDomain;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Entity
@Table(name = "order_items")
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OrderItemEntity extends BaseDomain {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @ManyToOne
    @JoinColumn(name = "order_id", nullable = false)
    OrderEntity order;

    @ManyToOne
    @JoinColumn(name = "item_id", nullable = false)
    ItemEntity item;

    @Column(nullable = false)
    Integer quantity;

}