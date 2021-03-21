package com.reloadly.transactionservice.vo;

import lombok.Data;

@Data
public class AccountResponseVO {
    private Long id;
    private ContactInfoVO contactInfo;
    private PersonalInfoVO personalInfo;
    private String accountNumber;
    private Double accountBalance;
}


