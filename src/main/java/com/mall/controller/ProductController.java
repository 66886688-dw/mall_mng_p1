package com.mall.controller;

import com.mall.common.Result;
import com.mall.entity.Product;
import com.mall.entity.ProductStatus;
import com.mall.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping
    public Result<Product> addProduct(@Validated @RequestBody Product product) {
        Product savedProduct = productService.addProduct(product);
        return Result.success(savedProduct);
    }

    @GetMapping("/{id}")
    public Result<Product> getProductById(@PathVariable Long id) {
        Product product = productService.getProductById(id);
        return Result.success(product);
    }

    @GetMapping
    public Result<List<Product>> getAllProducts() {
        List<Product> products = productService.getAllProducts();
        return Result.success(products);
    }

    @GetMapping("/status/{status}")
    public Result<List<Product>> getProductsByStatus(@PathVariable ProductStatus status) {
        List<Product> products = productService.getProductsByStatus(status);
        return Result.success(products);
    }

    @PutMapping
    public Result<Product> updateProduct(@Validated @RequestBody Product product) {
        Product updatedProduct = productService.updateProduct(product);
        return Result.success(updatedProduct);
    }

    @DeleteMapping("/{id}")
    public Result<Void> deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return Result.success();
    }

    @PutMapping("/{id}/on-shelf")
    public Result<Product> onShelf(@PathVariable Long id) {
        Product product = productService.onShelf(id);
        return Result.success(product);
    }

    @PutMapping("/{id}/off-shelf")
    public Result<Product> offShelf(@PathVariable Long id) {
        Product product = productService.offShelf(id);
        return Result.success(product);
    }
}
