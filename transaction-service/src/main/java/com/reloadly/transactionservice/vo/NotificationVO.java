package com.reloadly.transactionservice.vo;

import lombok.Data;

@Data
public class NotificationVO {

    private String mailTo;

    private String mailFrom;

    private String subject;

    private String text;

}
