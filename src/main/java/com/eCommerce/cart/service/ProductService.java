package com.eCommerce.cart.service;

import com.eCommerce.cart.dto.ProductDto;
import com.eCommerce.cart.dto.ProductImageDto;
import com.eCommerce.cart.dto.ProductReviewDto;
import com.eCommerce.cart.entity.Product;
import com.eCommerce.cart.entity.ProductReview;
import com.eCommerce.cart.repository.ProductRepository;
import com.eCommerce.cart.repository.ProductReviewRepository;
import com.eCommerce.cart.spec.ProductSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductReviewRepository productReviewRepository;

    public Map<String, Object> getAllProducts(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Product> products = productRepository.findAll(pageable);

        List<ProductDto> productDtos = products.getContent().stream().map(this::convertToDto).collect(Collectors.toList());

        Map<String, Object> response = new HashMap<>();
        response.put("products", productDtos);        // ✅ DTOs, not raw entities
        response.put("totalProducts", products.getTotalElements());

        return response;
    }


    public ProductDto convertToDto(Product product) {
        ProductDto dto = new ProductDto();
        dto.setId(product.getId());
        dto.setName(product.getName());
        dto.setPrice(product.getPrice());
        dto.setDescription(product.getDescription());
        dto.setRatings(product.getRatings());
        dto.setCategory(product.getCategory());
        dto.setSeller(product.getSeller());
        dto.setStock(product.getStock());
        dto.setNumOfReviews(product.getNumOfReviews());

        List<ProductReviewDto> reviewDtos = product.getReviews().stream().map(review -> {
            ProductReviewDto reviewDto = new ProductReviewDto();
            reviewDto.setProductId(review.getId());
            reviewDto.setComment(review.getComment());
            reviewDto.setRating(review.getRating());

            return reviewDto;
        }).collect(Collectors.toList());

        dto.setReviews(reviewDtos);

        List<ProductImageDto> imageDtos = product.getImages().stream().map(image -> {
            ProductImageDto imageDto = new ProductImageDto();
            imageDto.setUrl(image.getPublicId());

            return imageDto;
        }).collect(Collectors.toList());

        dto.setImages(imageDtos);

        return dto;
    }


    public ProductDto getProductById(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found with the ID " + id));
        return convertToDto(product);
    }


    public List<ProductDto> searchProducts(String category, Double minPrice, Double maxPrice, String keyword, Double ratings) {
        Specification<Product> spec = Specification
                .where(ProductSpecification.hasCategory(category))
                .and(ProductSpecification.priceBetween(minPrice, maxPrice))
                .and(ProductSpecification.hasNameOrDescriptionLike(keyword))
                .and(ProductSpecification.ratingGreaterThan(ratings));

        return productRepository.findAll(spec)
                .stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }


    public void addReview(ProductReviewDto reviewDto) {
        Product product = productRepository.findById(reviewDto.getProductId()).orElseThrow( () -> new RuntimeException("Product not found"));

        ProductReview review = new ProductReview();
        review.setComment(reviewDto.getComment());
        review.setRating(reviewDto.getRating());

        review.setProduct(product);

        productReviewRepository.save(review);
    }
}
