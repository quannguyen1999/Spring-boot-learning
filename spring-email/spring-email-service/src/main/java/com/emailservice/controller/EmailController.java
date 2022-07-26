package com.emailservice.controller;

import com.emailservice.model.Email;
import com.emailservice.model.EmailComposeDto;
import com.emailservice.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.MessagingException;

@RestController
public class EmailController {

    @Autowired
    private EmailService emailService;

    @PostMapping("/send")
    public ResponseEntity<String> sendEmail(@RequestBody Email email) throws Exception {
        emailService.sendMail(email);
        return ResponseEntity
                .status(HttpStatus.OK).body("send success");
    }

    @PostMapping("/composeEmail")
    public ResponseEntity<String> composeEmail(@RequestBody EmailComposeDto emailComposeDto) throws MessagingException {
        emailService.composeEmail(emailComposeDto);
        return ResponseEntity.status(HttpStatus.OK).body("send Success");
    }
}
