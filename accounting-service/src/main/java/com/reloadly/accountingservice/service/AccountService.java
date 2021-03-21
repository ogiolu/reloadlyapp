package com.reloadly.accountingservice.service;

import com.reloadly.accountingservice.model.AccountHolder;

import java.util.Optional;

public interface AccountService {
    AccountHolder save(AccountHolder accountHolder);

    AccountHolder update(AccountHolder accountHolder) throws Exception;

    AccountHolder performAccountOperation(Long accountId, Double amount, String type) throws Exception;

    Optional<AccountHolder> findById(Long id);

    AccountHolder findByAcountNumber(String accountno) throws Exception;
}
