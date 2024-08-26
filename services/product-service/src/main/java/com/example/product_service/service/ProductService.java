package com.example.product_service.service;

import com.example.product_service.exception.ProductPurchaseException;
import com.example.product_service.mapper.ProductMapper;
import com.example.product_service.model.Category;
import com.example.product_service.model.Product;
import com.example.product_service.model.request.ProductPurchaseInModel;
import com.example.product_service.model.request.ProductPurchaseRequest;
import com.example.product_service.model.request.ProductRequest;
import com.example.product_service.model.response.ProductGetAllResponse;
import com.example.product_service.model.response.ProductPurchaseOutModel;
import com.example.product_service.model.response.ProductPurchaseResponse;
import com.example.product_service.model.response.ProductResponse;
import com.example.product_service.repository.CategoryRepository;
import com.example.product_service.repository.ProductRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;
    private final CategoryRepository categoryRepository;
    private final Logger logger = LoggerFactory.getLogger(ProductService.class);

    public void createProduct(ProductRequest request) {
        Category category = categoryRepository.findById(request.getCategoryId())
                .orElseThrow(() -> new EntityNotFoundException("Category not found"));
        Product product = productMapper.mapRequestToProductEntity(request);
        product.setCategory(category);
        Product savedProduct = productRepository.save(product);
        logger.info("Product successfully created with the following id: " + savedProduct.getId());
    }

    @Transactional
    public ProductPurchaseResponse purchaseProducts(ProductPurchaseRequest request) {
        if (request == null || request.getProductPurchaseList().isEmpty()) {
            throw new IllegalArgumentException("Request or ProductPurchaseList cannot be null or empty");
        }

        List<Integer> productIdList = request
                .getProductPurchaseList()
                .stream()
                .map(ProductPurchaseInModel::getProductId)
                .toList();

        Map<Integer, Product> availableProductsMap = productRepository
                .getAvailableProducts(productIdList)
                .stream()
                .collect(Collectors.toMap(Product::getId, Function.identity()));

        if (availableProductsMap.size() != productIdList.size()) {
            throw new ProductPurchaseException("Some products that are requested do not exist");
        }

        List<ProductPurchaseOutModel> purchasedProducts = new ArrayList<>();
        for (ProductPurchaseInModel productPurchaseInModel : request.getProductPurchaseList()) {
            Product product = availableProductsMap.get(productPurchaseInModel.getProductId());

            if (product.getQuantity() < productPurchaseInModel.getQuantity()) {
                throw new ProductPurchaseException("Insufficient stock for product with id: " + productPurchaseInModel.getProductId());
            }

            product.setQuantity(product.getQuantity() - productPurchaseInModel.getQuantity());
            productRepository.save(product);

            purchasedProducts.add(productMapper.mapProductToPurchaseOutModel(product, productPurchaseInModel.getQuantity()));
        }
        return new ProductPurchaseResponse(purchasedProducts);
    }

    public ProductResponse getProduct(Integer productId) {
        return productRepository.findById(productId)
                .map(productMapper::mapProductEntityToResponse)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Unable to find product with the provided id:: " + productId
                ));
    }

    public ProductGetAllResponse getAllProducts() {
        return new ProductGetAllResponse(
                productRepository.findAll()
                .stream()
                    .map(productMapper::mapProductEntityToResponse)
                            .collect(Collectors.toList()));
    }
}
