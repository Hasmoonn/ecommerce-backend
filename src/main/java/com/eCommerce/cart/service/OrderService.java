package com.eCommerce.cart.service;

import com.eCommerce.cart.dto.*;
import com.eCommerce.cart.entity.Order;
import com.eCommerce.cart.entity.OrderItem;
import com.eCommerce.cart.entity.Product;
import com.eCommerce.cart.repository.OrderRepository;
import com.eCommerce.cart.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class OrderService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private OrderRepository orderRepository;

    public OrderCreated createOrder(CreateOrderRequest createOrderRequest) {
        Order order = new Order();

        order.setStatus("PENDING");
        double totalItemsAmount = 0;

        for (OrderItemDto item: createOrderRequest.getOrderItems()) {
            OrderItem orderItem = new OrderItem();

            orderItem.setName(item.getName());
            orderItem.setPrice(item.getPrice());
            orderItem.setImage(item.getImage());
            orderItem.setQuantity(item.getQuantity());

            Product product = productRepository.findById(item.getProductId()).orElseThrow(() -> new RuntimeException("Product not found"));

            orderItem.setProduct(product);

            totalItemsAmount += item.getPrice() * item.getQuantity();

            order.getOrderItems().add(orderItem);
        }

        order.setTotalItemsAmount(totalItemsAmount);
        double totalAmount = 0;
        double taxAmount = 10;
        totalAmount = totalItemsAmount + taxAmount;

        order.setTotalAmount(totalAmount);
        order.setTaxAmount(taxAmount);
        String refId = UUID.randomUUID().toString();
        order.setReferenceId(refId);

        orderRepository.save(order);

        return new OrderCreated(refId);
    }

    private OrderResponseDto mapToDto(Order order) {
        OrderResponseDto dto = new OrderResponseDto();
        dto.setId(order.getId());
        dto.setStatus(order.getStatus());
        dto.setReferenceId(order.getReferenceId());
        dto.setTotalItemsAmount(order.getTotalItemsAmount());
        dto.setTaxAmount(order.getTaxAmount());
        dto.setTotalAmount(order.getTotalAmount());

        List<OrderItemResponseDto> itemDtos = order.getOrderItems().stream()
                .map(item -> new OrderItemResponseDto(
                        item.getId(),
                        item.getName(),
                        item.getQuantity(),
                        item.getImage(),
                        item.getPrice(),
                        item.getProduct().getId()    // only the product ID
                ))
                .collect(Collectors.toList());

        dto.setOrderItems(itemDtos);
        return dto;
    }

    public OrderResponseDto getOrder(String referenceId) {
        Order order = orderRepository.findByReferenceId(referenceId).orElseThrow(() -> new  RuntimeException("No order found with this Reference Id"));

        return mapToDto(order);
    }
}
