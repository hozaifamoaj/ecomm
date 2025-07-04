package com.test.ecomm.entity;

import com.test.ecomm.common.BaseDomain;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Entity
@Table(name = "wishlists")
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class WishlistEntity extends BaseDomain {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    CustomerEntity customer;

    @ManyToOne
    @JoinColumn(name = "item_id", nullable = false)
    ItemEntity item;
}
