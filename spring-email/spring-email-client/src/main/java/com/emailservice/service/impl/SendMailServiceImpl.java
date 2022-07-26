package com.emailservice.service.impl;

import com.emailservice.constant.EmailDataKey;
import com.emailservice.enums.EmailTemplate;
import com.emailservice.feign.client.EmailServiceClient;
import com.emailservice.model.Email;
import com.emailservice.service.SendMailService;
import com.emailservice.util.CommonUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

import javax.mail.MessagingException;
import java.util.HashMap;
import java.util.Map;

@Service
@Slf4j
public class SendMailServiceImpl implements SendMailService {

    @Autowired
    private EmailServiceClient emailServiceClient;

    @Autowired
    private SpringTemplateEngine templateEngine;

    @Override
    public void sendEmail(Email email) throws MessagingException {
        validateEmail(email);
        email.setProperties(buildDynamicData());
        new Thread(() -> {
            log.info("emailTemplate={}.", email.getTemplate());
            log.info("toEmails={}", email.getToUser());
            log.info("ccEmails={}", email.getCcUser());
            log.info("dynamicData={}", CommonUtils.objToJson(email.getProperties()));

            email.setBody(buildHTMLEmailBodyByEmailTemplateType(email.getTemplate(), email.getProperties()));

            String response = emailServiceClient.sendMail(email).getBody();

            log.info(response);
        }).start();
    }

    private String buildHTMLEmailBodyByEmailTemplateType(EmailTemplate emailTemplate, Map<String, Object> dynamicData){
        Context context = new Context(null, dynamicData);

        // Build body
        return templateEngine.process(emailTemplate.templateFileName, context);
    }

    private HashMap<String, Object> buildDynamicData() {
        HashMap<String, Object> dynamicData = new HashMap<>();
        dynamicData.put(EmailDataKey.KEY_DEAR, "Quan hentai");
        dynamicData.put(EmailDataKey.KEY_PROBLEM_SUMMARY, "õ õ õ");
        return dynamicData;
    }

    private void validateEmail(Email email) throws MessagingException {

        if(ObjectUtils.isEmpty(email.getTemplate())){
            throw new MessagingException("template invalid");
        }

        if(ObjectUtils.isEmpty(email.getToUser())){
            throw new MessagingException("to user invalid");
        }

        if(ObjectUtils.isEmpty(email.getCcUser())){
            throw new MessagingException("cc user invalid");
        }

        if(StringUtils.isEmpty(email.getSubject())){
            throw new MessagingException("subject invalid");
        }
    }

}
