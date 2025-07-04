package com.test.ecomm.entity;

import com.test.ecomm.common.BaseDomain;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Entity
@Table(name = "customers")
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CustomerEntity extends BaseDomain {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(nullable = false)
    String name;

    @Column(nullable = false, unique = true)
    String email;
}
