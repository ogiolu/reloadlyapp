package com.reloadly.accountingservice.model;

import lombok.Data;

import javax.persistence.Embeddable;

@Data
@Embeddable
public class ContactInfo {

    private String address;

    private String phoneNumber;

    private String email;



}
