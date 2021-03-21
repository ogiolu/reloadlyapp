package com.reloadly.transactionservice.repository;

import com.reloadly.transactionservice.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
}
