package com.reloadly.transactionservice.service.impl;

import com.reloadly.transactionservice.model.Transaction;
import com.reloadly.transactionservice.repository.TransactionRepository;
import com.reloadly.transactionservice.service.TransactionService;
import com.reloadly.transactionservice.vo.AccountResponseVO;
import com.reloadly.transactionservice.vo.IDataResponse;
import com.reloadly.transactionservice.vo.NotificationVO;
import org.dozer.Mapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.transaction.Transactional;
import java.sql.Timestamp;
import java.util.Collections;
import java.util.Date;
import java.util.Optional;

@Service
public class TransactionServiceImpl implements TransactionService {

    private static final Logger LOGGER = LoggerFactory.getLogger(TransactionServiceImpl.class);
    @Autowired
    private TransactionRepository transactionRepository;

    private final Mapper mapper;

    @Value("${retrive.account.uri}")
    private String retireAccountUri;

    @Value("${account.opertaion.uri}")
    private String accountopertiouri;

    @Value("${mail.operation.uri}")
    private String mailoperationuri;

    @Autowired
    private RestTemplate restTemplate;

    public TransactionServiceImpl(Mapper mapper) {
        this.mapper = mapper;
    }

    @Override
    @Transactional
    public Transaction create(Transaction transaction) throws Exception {
        ResponseEntity<AccountResponseVO> response = getAccountResponseVOResponseEntity(transaction);
        AccountResponseVO accountResponseVO =response.getBody();
        validateTransaction(transaction, accountResponseVO);
        ResponseEntity<AccountResponseVO> responseOp=getAccountResponseVOResponseOperation(transaction);
        sendEmail(accountResponseVO,transaction);
        Date date =new Date();
        transaction.setTransDate(new Timestamp(date.getTime()));
        return   transactionRepository.save(transaction);

    }
    private ResponseEntity<AccountResponseVO> getAccountResponseVOResponseEntity(Transaction transaction) {
        LOGGER.info("retireAccountUri >>>>>>>>>>"+retireAccountUri);
        HttpHeaders headers = getHttpHeaders();
        HttpEntity request = new HttpEntity(headers);

        return restTemplate.exchange(
                retireAccountUri,
                HttpMethod.GET,
                request,
                AccountResponseVO.class,
                transaction.getAccountId()
        );
    }

    private ResponseEntity<AccountResponseVO> getAccountResponseVOResponseOperation(Transaction transaction) {
         LOGGER.info("accountopertiouri >>>>>>>>>>"+accountopertiouri);
        HttpHeaders headers = getHttpHeaders();
        HttpEntity request = new HttpEntity(headers);
        return restTemplate.exchange(
                accountopertiouri.replaceAll(":opertiontype",transaction.getTransactionType().toString())
                        .replaceAll(":accountid",transaction.getAccountId().toString())
                        .replaceAll(":amount",transaction.getTransctionAmount().toString()),
                HttpMethod.POST,
                request,
                AccountResponseVO.class
        );
    }

    private ResponseEntity<AccountResponseVO> sendEmail(AccountResponseVO accountResponseVO,Transaction transaction) {
        NotificationVO notificationVO = new NotificationVO();
        notificationVO.setMailTo(accountResponseVO.getContactInfo().getEmail());
        notificationVO.setSubject("Account Notification");
        notificationVO.setText("An operation occured on your account");
        LOGGER.info("mailoperationuri >>>>>>>>>>"+mailoperationuri);
        HttpHeaders headers = getHttpHeaders();
        HttpEntity<Object> request = new HttpEntity<>(notificationVO,headers);
        return restTemplate.exchange(
                mailoperationuri,
                HttpMethod.POST,
                request,
                AccountResponseVO.class
        );
    }

    private HttpHeaders getHttpHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        return headers;
    }

    private void validateTransaction(Transaction transaction, AccountResponseVO accountResponseVO) throws Exception {
        if(accountResponseVO.getId() == null){
            throw new Exception("Account Id  does not exist");
        }

        if (transaction.getStatus().toString().equalsIgnoreCase("DEBIT")){
            if (transaction.getTransctionAmount() >  accountResponseVO.getAccountBalance()){
                throw new  Exception("Amount is greater that the account balance");
            }
        }
    }

    @Override
    public Optional<Transaction> findById(Long id) {
        Optional<Transaction> transaction = transactionRepository.findById(id);
        return transaction;
    }


}
