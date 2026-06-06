package com.mall.mapper.impl;

import com.mall.entity.Product;
import com.mall.entity.ProductStatus;
import com.mall.mapper.ProductMapper;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

@Repository
public class ProductMapperImpl implements ProductMapper {

    private final Map<Long, Product> productMap = new ConcurrentHashMap<>();
    private final AtomicLong idGenerator = new AtomicLong(1);

    @Override
    public int insert(Product product) {
        long id = idGenerator.getAndIncrement();
        product.setId(id);
        productMap.put(id, product);
        return 1;
    }

    @Override
    public Product selectById(Long id) {
        return productMap.get(id);
    }

    @Override
    public List<Product> selectAll() {
        return new ArrayList<>(productMap.values());
    }

    @Override
    public List<Product> selectByStatus(ProductStatus status) {
        return productMap.values().stream()
                .filter(product -> product.getStatus() == status)
                .collect(Collectors.toList());
    }

    @Override
    public int updateById(Product product) {
        if (productMap.containsKey(product.getId())) {
            productMap.put(product.getId(), product);
            return 1;
        }
        return 0;
    }

    @Override
    public int deleteById(Long id) {
        return productMap.remove(id) != null ? 1 : 0;
    }
}
