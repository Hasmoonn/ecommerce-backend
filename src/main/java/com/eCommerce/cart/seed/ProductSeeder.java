package com.eCommerce.cart.seed;

import com.eCommerce.cart.entity.Product;
import com.eCommerce.cart.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProductSeeder implements CommandLineRunner {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public void run(String... args) throws Exception {
        if (productRepository.count() == 0) {
            List<Product> demoProducts = List.of(
                    new Product(null, "Apple iPhone 15", 799.0, "Smart Phone with A16 Chip", "Phone", 4.8, "Amazon", 50, List.of("/products/1.jpg")),
                    new Product(null, "Samsung Galaxy S24", 699.0, "Latest Android flagship phone", "Phone", 4.7, "Samsung Store", 40, List.of("/products/2.jpg")),
                    new Product(null, "Google Pixel 8", 649.0, "AI-powered camera smartphone", "Phone", 4.6, "Flipkart", 35, List.of("/products/3.jpg")),
                    new Product(null, "OnePlus 12", 599.0, "Fast and smooth performance phone", "Phone", 4.5, "OnePlus Store", 30, List.of("/products/4.jpg")),

                    new Product(null, "Dell XPS 13", 999.0, "Ultra-thin premium laptop", "Laptop", 4.7, "Dell", 20, List.of("/products/5.jpg")),
                    new Product(null, "MacBook Air M2", 1099.0, "Apple laptop with M2 chip", "Laptop", 4.9, "Apple Store", 25, List.of("/products/6.jpg")),
                    new Product(null, "HP Pavilion 15", 650.0, "Everyday use laptop", "Laptop", 4.3, "HP Store", 45, List.of("/products/7.jpg")),

                    new Product(null, "Sony WH-1000XM5", 349.0, "Noise cancelling headphones", "Accessories", 4.8, "Sony", 60, List.of("/products/8.jpg")),
                    new Product(null, "Apple AirPods Pro", 249.0, "Wireless earbuds with ANC", "Accessories", 4.7, "Apple Store", 70, List.of("/products/9.jpg")),

                    new Product(null, "Samsung 55\" 4K Smart TV", 799.0, "Ultra HD Smart Television", "Electronics", 4.6, "Samsung", 15, List.of("/products/10.jpg"))
            );

            productRepository.saveAll(demoProducts);

            System.out.println("Seeded Demo Products");
        } else {
            System.out.println("Products Already Exists! Skipping Seed");
        }
    }
}
