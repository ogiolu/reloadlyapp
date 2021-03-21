package com.reloadly.accountingservice.model;


import lombok.Data;

import javax.persistence.Embeddable;

@Data
@Embeddable
public class PersonalInfo {
    private String surName;
    private String firstName;
    private String otherName;
    private String title ;

}
