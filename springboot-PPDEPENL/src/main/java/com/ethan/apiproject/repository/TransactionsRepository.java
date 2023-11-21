package com.ethan.apiproject.repository;

import com.ethan.apiproject.model.Transactions;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface TransactionsRepository extends JpaRepository<Transactions, UUID> {
    List<Transactions> findAllByUser1IdOrUser2Id(UUID userId, UUID userId1);

    void deleteAllByUser1IdOrUser2Id(UUID userId, UUID userId1);
    List<Transactions> findByUser1IdOrUser2Id(UUID userId1, UUID userId2);

}
