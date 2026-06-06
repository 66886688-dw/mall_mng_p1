package com.mall.service;

import com.mall.entity.Product;
import com.mall.entity.ProductStatus;

import java.util.List;

public interface ProductService {
    Product addProduct(Product product);

    Product getProductById(Long id);

    List<Product> getAllProducts();

    List<Product> getProductsByStatus(ProductStatus status);

    Product updateProduct(Product product);

    void deleteProduct(Long id);

    Product onShelf(Long id);

    Product offShelf(Long id);
}
