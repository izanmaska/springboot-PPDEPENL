package com.ethan.apiproject.model;

import com.ethan.apiproject.model.enums.Currency;
import com.ethan.apiproject.model.ServiceEntity;

import java.math.BigDecimal;


public class ServiceDTO {
    private String name;
    private BigDecimal  price;
    private Currency currency;

    public ServiceDTO() {
    }

    public ServiceDTO(String name, BigDecimal price, Currency currency) {
        this.name = name;
        this.price = price;
        this.currency = currency;
    }

    public static ServiceDTO fromEntity(ServiceEntity service) {
        return new ServiceDTO(service.getName(), service.getPrice(), service.getCurrency());
    }

    public ServiceEntity toEntity() {
        ServiceEntity service = new ServiceEntity();
        service.setName(this.name);
        service.setPrice(this.price);
        service.setCurrency(this.currency);
        return service;
    }


}
