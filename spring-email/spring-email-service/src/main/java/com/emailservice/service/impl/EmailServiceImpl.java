package com.emailservice.service.impl;

import com.emailservice.enums.EmailTemplate;
import com.emailservice.model.EmailComposeDto;
import com.emailservice.model.Email;
import com.emailservice.model.EmailAttachment;
import com.emailservice.service.EmailService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Service
@RequiredArgsConstructor
@Slf4j
public class EmailServiceImpl implements EmailService {

    private final JavaMailSender emailSender;

    private final SpringTemplateEngine templateEngine;

    @Override
    public void sendMail(Email email) throws MessagingException {
        sendMailCommon(email);
    }

    @Override
    public void composeEmail(EmailComposeDto emailComposeDto) throws MessagingException {
        Map<String, Object> properties = new HashMap<>();
        properties.put("message",emailComposeDto.getMessage());
        emailComposeDto.setProperties(properties);


       sendMailCommon(Email.builder().toUser(emailComposeDto.getToUser())
               .ccUser(emailComposeDto.getCcUser())
               .properties(emailComposeDto.getProperties())
               .subject(emailComposeDto.getSubject())
               .body(emailComposeDto.getMessage())
               .template(EmailTemplate.EMAIL_TEMPLATE_COMPOSE)
               .build());
    }

    private String buildHTMLEmailBodyByEmailTemplateType(EmailTemplate emailTemplate, Map<String, Object> dynamicData){
        Context context = new Context(null, dynamicData);

        // Build body
        return templateEngine.process(emailTemplate.templateFileName, context);
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

    private void sendMailCommon(Email email) throws MessagingException {
        validateEmail(email);

        long start = System.currentTimeMillis();

        String[] ccUser = email.getToUser().toArray(new String[0]);
        String[] toUser = email.getCcUser().toArray(new String[0]);

        MimeMessage message = emailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED, StandardCharsets.UTF_8.name());
        Context context = new Context();

        context.setVariables(email.getProperties());
        if(!ObjectUtils.isEmpty(email.getEmailAttachments())){
            for (EmailAttachment emailAttachment : email.getEmailAttachments()) {
                helper.addAttachment(Objects.requireNonNull(emailAttachment.getFileName()),emailAttachment.getFile());
            }
        }

        helper.setTo(ccUser);
        helper.setCc(toUser);
        helper.setSubject(email.getSubject());

        String html = email.isComposeEmail() ?
                buildHTMLEmailBodyByEmailTemplateType(email.getTemplate(), email.getProperties()) :
                email.getBody();

        helper.setText(html, true);

        log.info("Sending email to {}", email.getToUser());
        log.info("Sending email cc {}", email.getCcUser());

        emailSender.send(message);

        long end = System.currentTimeMillis();
        log.info("Response time: {}s", (end - start) / 1000);
    }

}
