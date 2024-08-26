package com.example.product_service.mapper;

import com.example.product_service.model.Category;
import com.example.product_service.model.Product;
import com.example.product_service.model.request.ProductRequest;
import com.example.product_service.model.response.ProductPurchaseOutModel;
import com.example.product_service.model.response.ProductResponse;
import org.springframework.stereotype.Service;

@Service
public class ProductMapper {
    public Product mapRequestToProductEntity(ProductRequest request) {
        return Product.builder()
                .name(request.getName())
                .description(request.getDescription())
                .price(request.getPrice())
                .quantity(request.getQuantity())
                .build();
    }

    public ProductResponse mapProductEntityToResponse(Product product) {
        return new ProductResponse(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getQuantity(),
                product.getPrice(),
                product.getCategory().getId(),
                product.getCategory().getName(),
                product.getCategory().getDescription()
        );
    }

    public ProductPurchaseOutModel mapProductToPurchaseOutModel(Product product, double quantity) {
        return new ProductPurchaseOutModel(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getPrice(),
                quantity
        );
    }
}
