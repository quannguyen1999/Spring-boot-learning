package com.emailservice.controller;

import com.emailservice.model.Email;
import com.emailservice.service.SendMailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.MessagingException;

@RestController
public class DataHandlerController {

    @Autowired
    private SendMailService sendMailService;

    @PostMapping(value = "/sendMailBaseOnTemplate")
    private ResponseEntity<String> sendMailBaseOnTemplate(@RequestBody Email email) throws MessagingException {
        sendMailService.sendEmail(email);
        return ResponseEntity.ok("success");
    }
}
