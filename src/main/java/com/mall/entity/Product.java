package com.mall.entity;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class Product {
    private Long id;

    @NotBlank(message = "商品名称不能为空")
    private String name;

    private String image;

    @NotNull(message = "商品价格不能为空")
    @Positive(message = "商品价格必须大于0")
    private BigDecimal price;

    @NotBlank(message = "商品规格不能为空")
    private String spec;

    private ProductStatus status;

    @NotNull(message = "商品库存不能为空")
    @PositiveOrZero(message = "商品库存不能为负数")
    private Integer stock;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;
}
