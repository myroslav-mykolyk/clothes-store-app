package com.mykolyk.clothesstoreapp.repository;

import com.mykolyk.clothesstoreapp.model.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {

}
