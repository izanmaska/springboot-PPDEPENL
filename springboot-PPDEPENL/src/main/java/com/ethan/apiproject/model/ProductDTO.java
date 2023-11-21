package com.ethan.apiproject.model;

import com.ethan.apiproject.model.enums.Currency;

import java.math.BigDecimal;

public class ProductDTO {
    private String name;
    private BigDecimal  price;
    private Currency currency;

    public ProductDTO() {
    }

    public ProductDTO(String name, BigDecimal price, Currency currency) {
        this.name = name;
        this.price = price;
        this.currency = currency;
    }

    public static ProductDTO fromEntity(Product product) {
        return new ProductDTO(product.getName(), product.getPrice(), product.getCurrency());
    }

    public Product toEntity() {
        Product product = new Product();
        product.setName(this.name);
        product.setPrice(this.price);
        product.setCurrency(this.currency);
        return product;
    }


}
