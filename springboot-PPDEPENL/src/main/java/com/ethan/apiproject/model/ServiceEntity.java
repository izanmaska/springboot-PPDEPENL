package com.ethan.apiproject.model;

import com.ethan.apiproject.model.enums.Currency;
import com.ethan.apiproject.model.offerings.Offering;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "services")
public class ServiceEntity extends Offering {

    private String name;
    private String description;
    private Currency currency;


    public ServiceEntity() {
    }

    public ServiceEntity(String name, String description, Currency currency) {
        this.name = name;
        this.description = description;
        this.currency = currency;
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

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }
}
