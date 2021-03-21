package com.reloadly.notificationservice.service;

import com.reloadly.notificationservice.controller.NotificationController;
import com.reloadly.notificationservice.vo.NotificationVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {
    private JavaMailSender javaMailSender;
    private static final Logger LOGGER = LoggerFactory.getLogger(NotificationService.class);
    @Value("${mail.from}")
    private String emailFrom ;


    @Autowired
    public NotificationService(JavaMailSender javaMailSender){
        this.javaMailSender = javaMailSender;
    }

    @Async
    public void send(NotificationVo notificationVo) throws MailException, InterruptedException {

        Thread.sleep(10000);
        LOGGER.info("Sending Email ...");
        SimpleMailMessage mail = new SimpleMailMessage();
        mail.setTo(notificationVo.getMailTo());
        mail.setFrom(emailFrom);
        mail.setSubject(notificationVo.getSubject());
        mail.setText(notificationVo.getText());
        javaMailSender.send(mail);
        LOGGER.info("Email Sent! ...");
    }

}
