package com.reloadly.transactionservice.controller;

import com.reloadly.transactionservice.model.Transaction;
import com.reloadly.transactionservice.service.TransactionService;
import com.reloadly.transactionservice.vo.DataResponse;
import com.reloadly.transactionservice.vo.IDataResponse;
import com.reloadly.transactionservice.vo.Message;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.Collections;

@RestController
@RequestMapping("/app/transaction/v1" )
public class TransactionController {

    private static final Logger LOGGER = LoggerFactory.getLogger(TransactionController.class);

    @Autowired
    private TransactionService transactionService;

    @PostMapping("/create")
    public DataResponse create(@RequestBody @Valid Transaction transaction) {
        IDataResponse dataResponse = new IDataResponse();
        try {
            dataResponse.setData(Collections.singletonList(transactionService.create(transaction)));
            dataResponse.setValid(true);
            dataResponse.addMessage("Transaction SuccessFully Created", StringUtils.EMPTY, Message.Severity.SUCCESS);
        } catch( Exception e) {
            e.printStackTrace();
            dataResponse.setValid(false);
            dataResponse.addMessage("Error Occurred while Creating Transaction",e.getMessage(), Message.Severity.ERROR);
            dataResponse.setData(Collections.EMPTY_LIST);
        }

        return dataResponse;

    }

    @GetMapping("/retrievebyid/{id}")
    public DataResponse retrieveById(@PathVariable("id") Long id) {
        IDataResponse dataResponse = new IDataResponse();
        try {

            dataResponse.setData(Collections.singletonList(transactionService.findById(id)));
            dataResponse.setValid(true);
            dataResponse.addMessage("Transaction SuccessFully Retrieved", StringUtils.EMPTY, Message.Severity.SUCCESS);

        } catch( Exception e) {
            e.printStackTrace();
            dataResponse.setValid(false);
            dataResponse.addMessage("Error Occurred while Retrieving  Transaction",e.getMessage(), Message.Severity.ERROR);
            dataResponse.setData(Collections.EMPTY_LIST);
        }

        return dataResponse;

    }






}
