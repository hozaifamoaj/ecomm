package com.test.ecomm.repository;


import com.test.ecomm.dto.WishlistItemDto;
import com.test.ecomm.entity.WishlistEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WishlistRepository extends JpaRepository<WishlistEntity, Long> {


    //    @Query("SELECT new com.test.ecomm.dto.WishlistItemDto(i.id, i.name, i.price) " +
//            "FROM WishlistEntity w JOIN ItemEntity i ON w.item.id = i.id " +
//            "WHERE w.customer.id = :customerId")
//    List<WishlistItemDto> findWishlistItemsByCustomerId(Long customerId);
    @Query("SELECT new com.test.ecomm.dto.WishlistItemDto(i.id, i.name, i.price) " +
            "FROM WishlistEntity w JOIN ItemEntity i ON w.item = i " +
            "WHERE w.customer.id = :customerId")
    List<WishlistItemDto> findWishlistItemsByCustomerId(Long customerId);

}
