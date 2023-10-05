package com.mykolyk.clothesstoreapp.repository;

import com.mykolyk.clothesstoreapp.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order, Long> {
    @Query("SELECT o FROM Order o JOIN o.orderItems oi JOIN o.user u ORDER BY u.email ASC")
    Optional<Order> findAllByUser(String email);
}
