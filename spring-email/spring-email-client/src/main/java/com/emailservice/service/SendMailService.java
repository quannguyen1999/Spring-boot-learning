package com.emailservice.service;

import com.emailservice.enums.EmailTemplate;
import com.emailservice.model.Email;

import javax.mail.MessagingException;


public interface SendMailService {

    void sendEmail(Email email) throws MessagingException;

}
