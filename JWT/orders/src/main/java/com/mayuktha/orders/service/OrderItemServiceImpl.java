package com.mayuktha.orders.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.mayuktha.orders.controller.OrderItemController;
import com.mayuktha.orders.dto.OrderItemDto;
import com.mayuktha.orders.dto.OrderRequest;
import com.mayuktha.orders.dto.Response;
import com.mayuktha.orders.entity.Order;
import com.mayuktha.orders.entity.OrderItem;
import com.mayuktha.orders.enums.OrderStatus;
import com.mayuktha.orders.exceptions.NotFoundException;
import com.mayuktha.orders.feignclient.ProductClient;
import com.mayuktha.orders.feignclient.UserClient;
import com.mayuktha.orders.mapper.EntityDtoMapper;
import com.mayuktha.orders.repository.OrderItemRepo;
import com.mayuktha.orders.repository.OrderRepo;
import com.mayuktha.orders.response.ApiResponse;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderItemServiceImpl implements OrderItemService {


	 private static final Logger logger = LoggerFactory.getLogger(OrderItemServiceImpl.class);
	
    private final OrderRepo orderRepo;
    private final OrderItemRepo orderItemRepo;
   // private final ProductRepo productRepo;
   // private final UserService userService;
    private final EntityDtoMapper entityDtoMapper;
    
    @Autowired
    UserClient userClient;
    
    @Autowired
    ProductClient productClient;


    @Override
    public Response placeOrder(OrderRequest orderRequest) {

      //  User user = userService.getLoginUser();
        //map order request items to order entities
    	
    	logger.debug("orderRequest::::OrderItemServiceImpl::placeOrder:{}",SecurityContextHolder.getContext());
    	logger.debug("orderRequest::::OrderItemServiceImpl::placeOrder:{}",SecurityContextHolder.getContext().getAuthentication().getPrincipal());
    	
    	ResponseEntity<ApiResponse<Long>> response = userClient.fetchUserId(SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString());
    	logger.debug("orderRequest::::OrderItemServiceImpl::response.getBody().getData():{}",response.getBody().getData());
    	
    	List<OrderItem> orderItems = orderRequest.getItems().stream().map(orderItemRequest -> {
        	
    		ResponseEntity<Response> productResponse = productClient.getProductById(orderItemRequest.getProductId());
        	
            OrderItem orderItem = new OrderItem();
            orderItem.setProductid(productResponse.getBody().getProduct().getId());
            orderItem.setQuantity(orderItemRequest.getQuantity());
            orderItem.setPrice(productResponse.getBody().getProduct().getPrice().multiply(BigDecimal.valueOf(orderItemRequest.getQuantity()))); //set price according to the quantity
            orderItem.setStatus(OrderStatus.PENDING);
            orderItem.setUserid(response.getBody().getData());
            return orderItem;

        }).collect(Collectors.toList());

        //calculate the total price
        BigDecimal totalPrice = orderRequest.getTotalPrice() != null && orderRequest.getTotalPrice().compareTo(BigDecimal.ZERO) > 0
                ? orderRequest.getTotalPrice()
                : orderItems.stream().map(OrderItem::getPrice).reduce(BigDecimal.ZERO, BigDecimal::add);

        //create order entity
        Order order = new Order();
        order.setOrderItemList(orderItems);
        order.setTotalPrice(totalPrice);
        order.setUserId(response.getBody().getData());

        //set the order reference in each orderitem
        orderItems.forEach(orderItem -> orderItem.setOrder(order));

        orderRepo.save(order);

        return Response.builder()
                .status(200)
                .message("Order was successfully placed")
                .build();

    }

    @Override
    public Response updateOrderItemStatus(Long orderItemId, String status) {
        OrderItem orderItem = orderItemRepo.findById(orderItemId)
                .orElseThrow(()-> new NotFoundException("Order Item not found"));

        orderItem.setStatus(OrderStatus.valueOf(status.toUpperCase()));
        orderItemRepo.save(orderItem);
        return Response.builder()
                .status(200)
                .message("Order status updated successfully")
                .build();
    }

    @Override
    public Response filterOrderItems(OrderStatus status, LocalDateTime startDate, LocalDateTime endDate, Long itemId, Pageable pageable) {
    	logger.debug("filterOrderItems+++++++++++++++++++++++++++{}",status);
    	
        Specification<OrderItem> spec = Specification.where(OrderItemSpecification.hasStatus(status))
                .and(OrderItemSpecification.createdBetween(startDate, endDate))
                .and(OrderItemSpecification.hasItemId(itemId));

        Page<OrderItem> orderItemPage = orderItemRepo.findAll(spec, pageable);
        
        logger.debug("filterOrderItems++++++++++++orderItemPage+++++++++++++++{}",orderItemPage.isEmpty());

        if (orderItemPage.isEmpty()){
            throw new NotFoundException("No Order Found");
        }
        List<OrderItemDto> orderItemDtos = orderItemPage.getContent().stream()
                .map(entityDtoMapper::mapOrderItemToDtoPlusProductAndUser)
                .collect(Collectors.toList());
        logger.debug("filterOrderItems++++++++++orderItemDtos+++++++++++++++++{}",orderItemDtos);
        return Response.builder()
                .status(200)
                .orderItemList(orderItemDtos)
                .totalPage(orderItemPage.getTotalPages())
                .totalElement(orderItemPage.getTotalElements())
                .build();
    }

}