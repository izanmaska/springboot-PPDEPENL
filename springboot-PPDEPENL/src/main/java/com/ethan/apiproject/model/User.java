package com.ethan.apiproject.model;

import javax.persistence.*;

import java.util.*;

import com.ethan.apiproject.model.enums.Status;
import com.ethan.apiproject.model.enums.Type;
import com.ethan.apiproject.model.enums.UserRole;
import lombok.Data;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDateTime;
import java.util.stream.Collectors;

@Entity
@Table(name = "users")
@ToString
public class User {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(name = "id", columnDefinition = "VARCHAR(64)")
    private String id;

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

    @ElementCollection(targetClass = UserRole.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"))
    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    private Collection<UserRole> roles;

//    @ManyToMany(mappedBy = "users")
//    private List<Communities> communities;

    public User() {
    }

    public User(String id, String userName, String password, String email, Status status, Type userType, Set<UserRole> roles, LocalDateTime dateCreated, LocalDateTime dateUpdated) {
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

    public String getId() {
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

    public Collection<UserRole> getRoles() {
        return roles;
    }

    public void setId(String id) {
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

    public void setRoles(List<Role> roles) {
        this.roles = roles.stream()
                .map(Role::getName)
                .collect(Collectors.toList());
    }
    public static List<Role> convertToRolesSet(List<UserRole> userRoles) {
        return userRoles.stream()
                .map(role -> new Role(role))
                .collect(Collectors.toList());
    }

    public static User createTestUser(String userName, List<UserRole> userRoles) {
        User testUser = new User();
        testUser.setUserName(userName);
        testUser.setPassword("testPassword");
        testUser.setEmail(userName + "@example.com");
        testUser.setStatus(Status.ACTIVE);
        testUser.setUserType(Type.B2C);
        testUser.setRoles(convertToRolesSet(userRoles));
        testUser.setDateCreated(LocalDateTime.now());
        testUser.setDateUpdated(LocalDateTime.now());
        return testUser;
    }

}
