package com.mall.mapper;

import com.mall.entity.Product;
import com.mall.entity.ProductStatus;

import java.util.List;

public interface ProductMapper {
    int insert(Product product);

    Product selectById(Long id);

    List<Product> selectAll();

    List<Product> selectByStatus(ProductStatus status);

    int updateById(Product product);

    int deleteById(Long id);
}
