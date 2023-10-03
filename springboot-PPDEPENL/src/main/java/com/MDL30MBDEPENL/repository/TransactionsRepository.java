package com.MDL30MBDEPENL.repository;

import com.MDL30MBDEPENL.model.Communities;
import com.MDL30MBDEPENL.model.Transactions;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionsRepository extends JpaRepository<Transactions, Long> {
}
