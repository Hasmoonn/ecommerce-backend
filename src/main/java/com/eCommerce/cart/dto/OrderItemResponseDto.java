package com.eCommerce.cart.dto;

public class OrderItemResponseDto {
    private Long id;
    private String name;
    private Integer quantity;
    private String image;
    private Double price;
    private Long productId;      // only expose the productId, not the full product

    public OrderItemResponseDto() {}

    public OrderItemResponseDto(Long id, String name, Integer quantity, String image, Double price, Long productId) {
        this.id = id;
        this.name = name;
        this.quantity = quantity;
        this.image = image;
        this.price = price;
        this.productId = productId;
    }

    // getters and setters...

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }
}