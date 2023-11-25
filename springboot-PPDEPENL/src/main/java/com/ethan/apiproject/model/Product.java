package com.ethan.apiproject.model;

import com.ethan.apiproject.model.enums.Currency;
import com.ethan.apiproject.model.offerings.Offering;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "products")
public class Product extends Offering {

    private String name;
    private String description;

    public Product() {
    }

    public Product(String name, String description, Currency currency) {
        this.name = name;
        this.description = description;
        setCurrency(currency);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
