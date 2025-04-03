package com.mayuktha.orders.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mayuktha.orders.entity.Order;

public interface OrderRepo extends JpaRepository<Order, Long> {
}
