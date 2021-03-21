package com.reloadly.accountingservice.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class AccountHolder {
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    @Id
    private Long id;
    @Embedded
    private ContactInfo contactInfo;
    @Embedded
    private PersonalInfo personalInfo;

    private String accountNumber;

    private Double accountBalance;

}
