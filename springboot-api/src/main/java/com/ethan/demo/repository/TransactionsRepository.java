package com.ethan.demo.repository;

import com.ethan.demo.model.Communities;
import com.ethan.demo.model.Transactions;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionsRepository extends JpaRepository<Transactions, Long> {
}
