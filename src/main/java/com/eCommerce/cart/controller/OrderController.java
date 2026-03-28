package com.eCommerce.cart.controller;

import com.eCommerce.cart.dto.CreateOrderRequest;
import com.eCommerce.cart.dto.OrderCreated;
import com.eCommerce.cart.dto.OrderResponseDto;
import com.eCommerce.cart.entity.Order;
import com.eCommerce.cart.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping
    public ResponseEntity<?> createOrder(@RequestBody CreateOrderRequest createOrderRequest) {
        OrderCreated orderCreated = orderService.createOrder(createOrderRequest);

        return ResponseEntity.ok().body(orderCreated);
    }

    @GetMapping("/{referenceId}")
    public ResponseEntity<?> getOrder(@PathVariable String referenceId) {
        OrderResponseDto orderResponseDto = orderService.getOrder(referenceId);

        return ResponseEntity.ok().body(orderResponseDto);
    }
}
