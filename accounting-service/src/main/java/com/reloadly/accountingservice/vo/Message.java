package com.reloadly.accountingservice.vo;

public interface Message {

    String getSummary();

    void setSummary(String summary);

    String getDetail();

    void setDetail(String detail);

    Message.Severity getSeverity();

    void setSeverity(Message.Severity severity);

    enum Severity {
        SUCCESS,
        INFO,
        WARNING,
        ERROR;
        Severity() {
        }
    }
}
