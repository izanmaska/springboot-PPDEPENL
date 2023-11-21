package com.ethan.apiproject.model;

import javax.persistence.*;

import java.util.Set;
import java.util.UUID;

import com.ethan.apiproject.model.enums.Status;
import com.ethan.apiproject.model.enums.Type;
import com.ethan.apiproject.model.enums.UserRole;
import org.hibernate.annotations.GenericGenerator;


import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "users")
public class Users {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(name = "id", columnDefinition = "VARCHAR(32)")
    private UUID id;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "password")
    private String password;

    @Column(name = "email")
    private String email;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private Status status;


    @Enumerated(EnumType.STRING)
    @Column(name = "user_type")
    private Type userType;

    @Column(name = "date_created")
    private LocalDateTime dateCreated;

    @Column(name = "date_updated")
    private LocalDateTime dateUpdated;
    @ElementCollection(targetClass = UserRole.class)
    @CollectionTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"))
    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    private Set<UserRole> roles;


    @ManyToMany(mappedBy = "users")
    private List<Communities> communities;


    public Users() {
    }

    public Users(UUID id, String userName,String password, String email, Status status, Type userType, Set<UserRole> roles, LocalDateTime dateCreated, LocalDateTime dateUpdated) {
        this.id = id;
        this.userName = userName;
        this.password = password;
        this.email = email;
        this.status = status;
        this.userType = userType;
        this.roles = roles;
        this.dateCreated = dateCreated;
        this.dateUpdated = dateUpdated;

    }


    public UUID getId() {
        return id;
    }

    public String getUserName() {
        return userName;
    }
    public String getPassword() {
        return password;
    }
    public String getEmail() {
        return email;
    }

    public Status getStatus() {
        return status;
    }

    public Type getUserType() {
        return userType;
    }

    public LocalDateTime getDateCreated() {
        return dateCreated;
    }

    public LocalDateTime getDateUpdated() {
        return dateUpdated;
    }


    // Setter methods
    public void setId(UUID id) {
        this.id = id;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
    public void setPassword(String password) {
        this.password = password;
    }


    public void setEmail(String email) {
        this.email = email;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public void setUserType(Type userType) {
        this.userType = userType;
    }


    public void setDateCreated(LocalDateTime dateCreated) {
        this.dateCreated = dateCreated;
    }

    public void setDateUpdated(LocalDateTime dateUpdated) {
        this.dateUpdated = dateUpdated;
    }


    public void setRoles(Set<Role> roles) {this.roles = roles;
    }
}