package com.MDL30MBDEPENL.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "communities")
public class Communities {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "desc")
    private String desc;

    @Column(name = "owner_id")
    private Long ownerId;

    @Column(name = "owner_username")
    private String ownerUsername;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private Status status;

    @Column(name = "date_created")
    private LocalDateTime dateCreated;

    @Column(name = "date_updated")
    private LocalDateTime dateUpdated;

    @Column(name = "url")
    private String url;

    @OneToMany(mappedBy = "community", cascade = CascadeType.ALL)
    private List<Users> users;

    public Communities() {
    }

    public Communities(Long id, String name, String desc, Long ownerId, String ownerUsername, Status status, LocalDateTime dateCreated, LocalDateTime dateUpdated, String url, List<Users> users) {
        this.id = id;
        this.name = name;
        this.desc = desc;
        this.ownerId = ownerId;
        this.ownerUsername = ownerUsername;
        this.status = status;
        this.dateCreated = dateCreated;
        this.dateUpdated = dateUpdated;
        this.url = url;
        this.users = users;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDesc() {
        return desc;
    }

    public Long getOwnerId() {
        return ownerId;
    }

    public String getOwnerUsername() {
        return ownerUsername;
    }

    public Status getStatus() {
        return status;
    }

    public LocalDateTime getDateCreated() {
        return dateCreated;
    }

    public LocalDateTime getDateUpdated() {
        return dateUpdated;
    }

    public String getUrl() {
        return url;
    }

    public List<Users> getUsers() {
        return users;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public void setOwnerId(Long ownerId) {
        this.ownerId = ownerId;
    }

    public void setOwnerUsername(String ownerUsername) {
        this.ownerUsername = ownerUsername;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public void setDateCreated(LocalDateTime dateCreated) {
        this.dateCreated = dateCreated;
    }

    public void setDateUpdated(LocalDateTime dateUpdated) {
        this.dateUpdated = dateUpdated;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setUsers(List<Users> users) {
        this.users = users;
    }

}