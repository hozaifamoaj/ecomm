package com.test.ecomm.entity;

import com.test.ecomm.common.BaseDomain;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;

@Entity
@Table(name = "items")
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ItemEntity extends BaseDomain {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(nullable = false)
    String name;

    @Column(nullable = false)
    BigDecimal price;
}
