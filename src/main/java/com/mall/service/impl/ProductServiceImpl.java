package com.mall.service.impl;

import com.mall.entity.Product;
import com.mall.entity.ProductStatus;
import com.mall.exception.BusinessException;
import com.mall.mapper.ProductMapper;
import com.mall.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductMapper productMapper;

    @Override
    public Product addProduct(Product product) {
        product.setStatus(ProductStatus.NEW);
        product.setCreateTime(LocalDateTime.now());
        product.setUpdateTime(LocalDateTime.now());
        productMapper.insert(product);
        log.info("新增商品成功，商品ID: {}, 商品名称: {}", product.getId(), product.getName());
        return product;
    }

    @Override
    public Product getProductById(Long id) {
        Product product = productMapper.selectById(id);
        if (product == null) {
            throw new BusinessException("商品不存在，ID: " + id);
        }
        return product;
    }

    @Override
    public List<Product> getAllProducts() {
        return productMapper.selectAll();
    }

    @Override
    public List<Product> getProductsByStatus(ProductStatus status) {
        return productMapper.selectByStatus(status);
    }

    @Override
    public Product updateProduct(Product product) {
        Product existProduct = getProductById(product.getId());
        product.setStatus(existProduct.getStatus());
        product.setCreateTime(existProduct.getCreateTime());
        product.setUpdateTime(LocalDateTime.now());
        int rows = productMapper.updateById(product);
        if (rows == 0) {
            throw new BusinessException("更新商品失败，ID: " + product.getId());
        }
        log.info("更新商品成功，商品ID: {}", product.getId());
        return product;
    }

    @Override
    public void deleteProduct(Long id) {
        getProductById(id);
        int rows = productMapper.deleteById(id);
        if (rows == 0) {
            throw new BusinessException("删除商品失败，ID: " + id);
        }
        log.info("删除商品成功，商品ID: {}", id);
    }

    @Override
    public Product onShelf(Long id) {
        Product product = getProductById(id);
        if (product.getStatus() == ProductStatus.ON_SHELF) {
            throw new BusinessException("商品已上架，无需重复操作");
        }
        product.setStatus(ProductStatus.ON_SHELF);
        product.setUpdateTime(LocalDateTime.now());
        productMapper.updateById(product);
        log.info("商品上架成功，商品ID: {}", id);
        return product;
    }

    @Override
    public Product offShelf(Long id) {
        Product product = getProductById(id);
        if (product.getStatus() == ProductStatus.OFF_SHELF) {
            throw new BusinessException("商品已下架，无需重复操作");
        }
        product.setStatus(ProductStatus.OFF_SHELF);
        product.setUpdateTime(LocalDateTime.now());
        productMapper.updateById(product);
        log.info("商品下架成功，商品ID: {}", id);
        return product;
    }
}
