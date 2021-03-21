package com.reloadly.notificationservice.vo;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Data
public class NotificationVo {
    @NotNull
    @Email
    private String mailTo;
    @NotNull
    private String subject;
    @NotNull
    private String text;

}
