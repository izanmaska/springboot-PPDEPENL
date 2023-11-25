package com.ethan.apiproject.model;

import com.ethan.apiproject.model.enums.UserRole;

import javax.persistence.*;

@Entity
@Table(name = "roles")
public class Role {
    @Id
    @Enumerated(EnumType.STRING)
    @Column(length = 10)
    private UserRole name;

    public Role() {
    }

    public Role(UserRole name) {
        this.name = name;
    }

    public UserRole getName() {
        return name;
    }

    public void setName(UserRole name) {
        this.name = name;
    }
}
