package com.reloadly.accountingservice.controller;

import com.reloadly.accountingservice.model.AccountHolder;
import com.reloadly.accountingservice.service.AccountService;
import com.reloadly.accountingservice.vo.DataResponse;
import com.reloadly.accountingservice.vo.IDataResponse;
import com.reloadly.accountingservice.vo.Message;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.Collections;
import java.util.Optional;

@RestController
@RequestMapping("/app/account/v1" )
public class AccountController {

    private static final Logger LOGGER = LoggerFactory.getLogger(AccountController.class);

    @Autowired
    private AccountService accountService;

    @PostMapping("/create")
    public DataResponse create(@RequestBody @Valid AccountHolder accountHolder) {
        IDataResponse dataResponse = new IDataResponse();
        try {
            dataResponse.setData(Collections.singletonList(accountService.save(accountHolder)));
            dataResponse.setValid(true);
            dataResponse.addMessage("Account SuccessFully Created", StringUtils.EMPTY, Message.Severity.SUCCESS);
        } catch( Exception e) {
            e.printStackTrace();
            dataResponse.setValid(false);
            dataResponse.addMessage("Error Occurred while Creating Account",e.getMessage(), Message.Severity.ERROR);
            dataResponse.setData(Collections.EMPTY_LIST);
        }

        return dataResponse;

    }

    @PutMapping("/update")
    public DataResponse update(@RequestBody @Valid AccountHolder accountHolder) {
        IDataResponse dataResponse = new IDataResponse();
        try {
            dataResponse.setData(Collections.singletonList(accountService.update(accountHolder)));
            dataResponse.setValid(true);
            dataResponse.addMessage("Account SuccessFully Updated", StringUtils.EMPTY, Message.Severity.SUCCESS);
        } catch( Exception e) {
            e.printStackTrace();
            dataResponse.setValid(false);
            dataResponse.addMessage("Error Occurred while Updating Account",e.getMessage(), Message.Severity.ERROR);
            dataResponse.setData(Collections.EMPTY_LIST);
        }

        return dataResponse;

    }

    @GetMapping("/retrievebyid/{id}")
    public AccountHolder retrieveById(@PathVariable("id") Long id) {
        IDataResponse dataResponse = new IDataResponse();
        AccountHolder account =new AccountHolder();
        Optional<AccountHolder>  accountHolder =accountService.findById(id);
        if(accountHolder.isPresent())
        {
            account= accountHolder.get();
        }
        return account;

    }

    @GetMapping("/retrievebyaccountno/{accountno}")
    public AccountHolder retrieveById(@PathVariable("accountno") String accountno) throws Exception {
        AccountHolder accountHolder =accountService.findByAcountNumber(accountno);
        return accountHolder;

    }

    @PostMapping ("/acountopertion/{opertiontype}/{accountid}/{amount}")
    public AccountHolder  accountOpertion(@PathVariable("opertiontype") String opertiontype,@PathVariable("accountid") String accountid,@PathVariable("amount") String amount) throws Exception {
        AccountHolder accountHolder = new AccountHolder();
        if(StringUtils.isNotBlank(accountid) && StringUtils.isNotBlank(amount) ){
            accountHolder= accountService.performAccountOperation( Long.parseLong(accountid),  Double.parseDouble(amount),  opertiontype);
        }
     return accountHolder;

    }



}
