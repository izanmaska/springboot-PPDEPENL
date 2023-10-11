package com.ethan.apiproject.repository;

import com.ethan.apiproject.model.Transactions;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransactionsRepository extends JpaRepository<Transactions, Long> {
    List<Transactions> findAllByUser1IdOrUser2Id(Long userId, Long userId1);

    void deleteAllByUser1IdOrUser2Id(Long userId, Long userId1);
    List<Transactions> findByUser1IdOrUser2Id(Long userId1, Long userId2);

}
