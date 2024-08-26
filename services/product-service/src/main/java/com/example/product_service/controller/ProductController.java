package com.example.product_service.controller;


import com.example.product_service.model.request.ProductPurchaseRequest;
import com.example.product_service.model.request.ProductRequest;
import com.example.product_service.model.response.ProductGetAllResponse;
import com.example.product_service.model.response.ProductPurchaseResponse;
import com.example.product_service.model.response.ProductResponse;
import com.example.product_service.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping
    public ResponseEntity<Void> createProduct(@RequestBody @Valid ProductRequest request) {
        productService.createProduct(request);
        return ResponseEntity.accepted().build();
    }

    @PostMapping("/purchase")
    public ResponseEntity<ProductPurchaseResponse> purchaseProducts(@RequestBody ProductPurchaseRequest request) {
        return ResponseEntity.ok(productService.purchaseProducts(request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponse> getProduct(@PathVariable("id") Integer productId) {
        return ResponseEntity.ok(productService.getProduct(productId));
    }

    @GetMapping
    public ResponseEntity<ProductGetAllResponse> getAllProducts() {
        return ResponseEntity.ok(productService.getAllProducts());
    }
}
