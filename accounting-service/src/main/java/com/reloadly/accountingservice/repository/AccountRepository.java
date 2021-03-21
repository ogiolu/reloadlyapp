package com.reloadly.accountingservice.repository;

import com.reloadly.accountingservice.model.AccountHolder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository   extends JpaRepository<AccountHolder, Long> {
    AccountHolder findAccountHolderByAccountNumber(String accountnumber);
}
