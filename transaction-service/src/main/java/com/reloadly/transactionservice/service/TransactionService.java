package com.reloadly.transactionservice.service;

import com.reloadly.transactionservice.model.Transaction;

import java.util.Optional;

public interface TransactionService {
    Transaction create(Transaction transaction) throws Exception;

    Optional<Transaction> findById(Long id);

}
