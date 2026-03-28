package com.eCommerce.cart.dto;

import com.eCommerce.cart.entity.Order;

public class OrderCreated {
    private String referenceId;

    public OrderCreated() {
    }

    public OrderCreated(String referenceId) {
        super();
        this.referenceId = referenceId;
    }

    public String getReferenceId() {
        return referenceId;
    }

    public void setReferenceId(String referenceId) {
        this.referenceId = referenceId;
    }
}
