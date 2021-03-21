package com.reloadly.accountingservice.service.impl;

import com.reloadly.accountingservice.model.AccountHolder;
import com.reloadly.accountingservice.repository.AccountRepository;
import com.reloadly.accountingservice.service.AccountService;
import org.apache.commons.lang.StringUtils;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AccountServiceImpl implements AccountService {


    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private  Mapper mapper;

    @Override
    public AccountHolder save(AccountHolder accountHolder) {
        return   accountRepository.save(accountHolder);

    }

    @Override
    public AccountHolder update(AccountHolder account) throws Exception {
        checkIfAccountExists(account.getId());
        return   accountRepository.save(account);
    }

    private AccountHolder checkIfAccountExists(Long accountId) throws Exception {
        Optional<AccountHolder> accountHolder = accountRepository.findById(accountId);
        if (!accountHolder.isPresent()){
            throw  new Exception("Account Id does not exist");
        }
        return accountHolder.get();
    }

    public void debitAccount(){

    }

    @Override
    public AccountHolder performAccountOperation(Long accountId, Double amount, String type) throws Exception {
        AccountHolder accountHolder= checkIfAccountExists(accountId);
        if (StringUtils.isNotBlank(type)){
            switch (type){
                case "DEBIT":{
                    accountHolder.setAccountBalance(accountHolder.getAccountBalance() - amount);
                }
                break;
                case "CREDIT":{
                    accountHolder.setAccountBalance(accountHolder.getAccountBalance() + amount);
                }
                break;

            }
            accountHolder=   accountRepository.save(accountHolder);
        }
        else{
            throw  new Exception("Invalid Operation type");
        }
        return accountHolder;
    }

    @Override
    public Optional<AccountHolder> findById(Long id) {
        Optional<AccountHolder> accountHolder = accountRepository.findById(id);
        return accountHolder;
    }

    @Override
    public AccountHolder findByAcountNumber(String accountno) throws Exception {

        AccountHolder accountHolder = accountRepository.findAccountHolderByAccountNumber(accountno);
        if (accountHolder == null){
            throw new Exception("Account does not exist");
        }

        return accountHolder;
    }

}
