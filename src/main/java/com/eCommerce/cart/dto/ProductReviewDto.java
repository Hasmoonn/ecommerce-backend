package com.eCommerce.cart.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class ProductReviewDto {

    @NotNull(message = "Product Id is required")
    private Long productId;

    @NotNull(message = "Rating is required")
    private Double rating;

    @NotBlank(message = "Comment cannot be null")
    private String comment;

    public ProductReviewDto(){

    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
