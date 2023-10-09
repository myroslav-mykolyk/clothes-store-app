package com.mykolyk.clothesstoreapp.repository;

import com.mykolyk.clothesstoreapp.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order, Long> {
    @Query("SELECT o FROM Order o JOIN o.orderItems oi JOIN o.user u WHERE u.email = :email")
    Optional<Order> findAllByUserEmail(String email);
}
