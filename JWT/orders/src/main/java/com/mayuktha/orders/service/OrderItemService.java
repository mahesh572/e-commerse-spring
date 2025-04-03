package com.mayuktha.orders.service;

import java.time.LocalDateTime;

import org.springframework.data.domain.Pageable;

import com.mayuktha.orders.dto.OrderRequest;
import com.mayuktha.orders.dto.Response;
import com.mayuktha.orders.enums.OrderStatus;

public interface OrderItemService {
    Response placeOrder(OrderRequest orderRequest);
    Response updateOrderItemStatus(Long orderItemId, String status);
    Response filterOrderItems(OrderStatus status, LocalDateTime startDate, LocalDateTime endDate, Long itemId, Pageable pageable);
}
