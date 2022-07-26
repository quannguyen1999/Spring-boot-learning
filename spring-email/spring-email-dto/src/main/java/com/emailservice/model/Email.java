package com.emailservice.model;

import com.emailservice.enums.EmailTemplate;
import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Builder
@Data
public class Email {

    private List<String> toUser;

    private List<String> ccUser;

    private String subject;

    private String body;

    private EmailTemplate template;

    Map<String, Object> properties = new HashMap<>();

    List<EmailAttachment> emailAttachments = new ArrayList<>();

    private boolean isComposeEmail = true;

}
