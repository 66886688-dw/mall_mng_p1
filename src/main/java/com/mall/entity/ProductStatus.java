package com.mall.entity;

public enum ProductStatus {
    NEW("新建"),
    PENDING_REVIEW("待审核"),
    ON_SHELF("上架"),
    OFF_SHELF("下架");

    private final String description;

    ProductStatus(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
