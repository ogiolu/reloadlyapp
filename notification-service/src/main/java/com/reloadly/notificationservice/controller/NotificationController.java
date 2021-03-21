package com.reloadly.notificationservice.controller;

import com.reloadly.notificationservice.service.NotificationService;
import com.reloadly.notificationservice.vo.DataResponse;
import com.reloadly.notificationservice.vo.IDataResponse;
import com.reloadly.notificationservice.vo.Message;
import com.reloadly.notificationservice.vo.NotificationVo;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.Collections;

@RestController
@RequestMapping("/app/mail/v1" )
public class NotificationController {

    private static final Logger LOGGER = LoggerFactory.getLogger(NotificationController.class);

    @Autowired
    private NotificationService notificationService;

    @PostMapping("/sendmail")
    public void sendMail(@RequestBody @Valid NotificationVo notificationVo) {

        try {
            notificationService.send(notificationVo);
            LOGGER.info("Email Sent");

        } catch( Exception e) {
            e.printStackTrace();
            LOGGER.error("Error Occured while sending email >>"+e.getMessage());

        }


    }




}
