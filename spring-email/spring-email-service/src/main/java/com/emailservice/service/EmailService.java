package com.emailservice.service;

import com.emailservice.model.EmailComposeDto;
import com.emailservice.model.Email;

import javax.mail.MessagingException;

public interface EmailService {
    void sendMail(Email email) throws MessagingException;

    void composeEmail(EmailComposeDto email) throws MessagingException;

}
