package com.mall.service;

import com.mall.entity.Product;
import com.mall.entity.ProductStatus;
import com.mall.exception.BusinessException;
import com.mall.MallMngApplication;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = MallMngApplication.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ProductServiceTest {

    @Autowired
    private ProductService productService;

    private static Long productId;

    @Test
    @Order(1)
    void testAddProduct() {
        Product product = new Product();
        product.setName("测试商品");
        product.setImage("test.jpg");
        product.setPrice(new BigDecimal("99.99"));
        product.setSpec("标准规格");
        product.setStock(100);

        Product result = productService.addProduct(product);

        assertNotNull(result);
        assertNotNull(result.getId());
        assertEquals("测试商品", result.getName());
        assertEquals(ProductStatus.NEW, result.getStatus());
        assertEquals(100, result.getStock());
        assertNotNull(result.getCreateTime());

        productId = result.getId();
    }

    @Test
    @Order(2)
    void testGetProductById() {
        Product product = productService.getProductById(productId);

        assertNotNull(product);
        assertEquals(productId, product.getId());
        assertEquals("测试商品", product.getName());
    }

    @Test
    @Order(3)
    void testGetProductByIdNotFound() {
        assertThrows(BusinessException.class, () -> productService.getProductById(999L));
    }

    @Test
    @Order(4)
    void testGetAllProducts() {
        List<Product> products = productService.getAllProducts();

        assertNotNull(products);
        assertFalse(products.isEmpty());
    }

    @Test
    @Order(5)
    void testGetProductsByStatus() {
        List<Product> products = productService.getProductsByStatus(ProductStatus.NEW);

        assertNotNull(products);
        assertFalse(products.isEmpty());
        assertTrue(products.stream().allMatch(p -> p.getStatus() == ProductStatus.NEW));
    }

    @Test
    @Order(6)
    void testUpdateProduct() {
        Product product = productService.getProductById(productId);
        product.setName("更新后的商品名称");
        product.setPrice(new BigDecimal("199.99"));

        Product result = productService.updateProduct(product);

        assertNotNull(result);
        assertEquals("更新后的商品名称", result.getName());
        assertEquals(new BigDecimal("199.99"), result.getPrice());
    }

    @Test
    @Order(7)
    void testOnShelf() {
        Product result = productService.onShelf(productId);

        assertNotNull(result);
        assertEquals(ProductStatus.ON_SHELF, result.getStatus());
    }

    @Test
    @Order(8)
    void testOnShelfAlreadyOnShelf() {
        assertThrows(BusinessException.class, () -> productService.onShelf(productId));
    }

    @Test
    @Order(9)
    void testOffShelf() {
        Product result = productService.offShelf(productId);

        assertNotNull(result);
        assertEquals(ProductStatus.OFF_SHELF, result.getStatus());
    }

    @Test
    @Order(10)
    void testOffShelfAlreadyOffShelf() {
        assertThrows(BusinessException.class, () -> productService.offShelf(productId));
    }

    @Test
    @Order(11)
    void testDeleteProduct() {
        assertDoesNotThrow(() -> productService.deleteProduct(productId));
        assertThrows(BusinessException.class, () -> productService.getProductById(productId));
    }
}
