package com.ethan.apiproject.model;

import java.time.LocalDateTime;
import java.util.UUID;

import com.ethan.apiproject.model.enums.Type;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;


@Entity
@Table(name = "transactions")
public class Transactions {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(name = "id", columnDefinition = "VARCHAR(32)")
    private UUID id;


    @Column(name = "user1_id")
    private String user1Id;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "user1_type", nullable = false)
    private Type user1Type;

    @Column(name = "user2_id")
    private String user2Id;

    @Column(name = "user2_type")
    private Type user2Type;

    @Column(name = "date")
    private LocalDateTime date;



    public Transactions() {
    }

    public Transactions(UUID id, String user1Id, Type user1Type, String user2Id, Type user2Type, LocalDateTime date) {
        this.id = id;
        this.user1Id = user1Id;
        this.user1Type = user1Type;
        this.user2Id = user2Id;
        this.user2Type = user2Type;
        this.date = date;

    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getUser1Id() {
        return user1Id;
    }

    public void setUser1Id(String user1Id) {
        this.user1Id = user1Id;
    }

    public Type getUser1Type() {
        return user1Type;
    }

    public void setUser1Type(Type user1Type) {
        this.user1Type = user1Type;
    }

    public String getUser2Id() {
        return user2Id;
    }

    public void setUser2Id(String user2Id) {
        this.user2Id = user2Id;
    }

    public Type getUser2Type() {
        return user2Type;
    }

    public void setUser2Type(Type user2Type) {
        this.user2Type = user2Type;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }



}