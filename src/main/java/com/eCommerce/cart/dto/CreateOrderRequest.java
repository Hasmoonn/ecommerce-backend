package com.eCommerce.cart.dto;

import java.util.List;

public class CreateOrderRequest {
    private List<OrderItemDto> orderItems;

    public CreateOrderRequest() {
    }

    public List<OrderItemDto> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItemDto> orderItems) {
        this.orderItems = orderItems;
    }
}
