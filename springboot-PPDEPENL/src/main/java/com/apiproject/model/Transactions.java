package com.apiproject.model;

import java.time.LocalDateTime;

import jakarta.persistence.*;


@Entity
@Table(name = "transactions")
public class Transactions {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user1_id")
    private Long user1Id;

    @Column(name = "user1_type")
    private String user1Type;

    @Column(name = "user2_id")
    private Long user2Id;

    @Column(name = "user2_type")
    private String user2Type;

    @Column(name = "date")
    private LocalDateTime date;

    @Column(name = "url")
    private String url;

    public Transactions() {
    }

    public Transactions(Long id, Long user1Id, String user1Type, Long user2Id, String user2Type, LocalDateTime date, String url) {
        this.id = id;
        this.user1Id = user1Id;
        this.user1Type = user1Type;
        this.user2Id = user2Id;
        this.user2Type = user2Type;
        this.date = date;
        this.url = url;
    }
    // Getter methods

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUser1Id() {
        return user1Id;
    }

    public void setUser1Id(Long user1Id) {
        this.user1Id = user1Id;
    }

    public String getUser1Type() {
        return user1Type;
    }

    public void setUser1Type(String user1Type) {
        this.user1Type = user1Type;
    }

    public Long getUser2Id() {
        return user2Id;
    }

    public void setUser2Id(Long user2Id) {
        this.user2Id = user2Id;
    }

    public String getUser2Type() {
        return user2Type;
    }

    public void setUser2Type(String user2Type) {
        this.user2Type = user2Type;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

}