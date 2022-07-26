package com.emailservice.enums;

import com.emailservice.constant.EmailSubjectConstants;
import com.emailservice.constant.EmailTemplateFileConstant;

public enum EmailTemplate {
    EMAIL_TEMPLATE_COMPOSE(0,EmailSubjectConstants.SUBJECT_TEMPLATE_COMPOSE, EmailTemplateFileConstant.TEMPLATE_COMPOSE),
    EMAIL_TEMPLATE_1(1,EmailSubjectConstants.SUBJECT_TEMPLATE_1, EmailTemplateFileConstant.TEMPLATE_1),
    EMAIL_TEMPLATE_2(2,EmailSubjectConstants.SUBJECT_TEMPLATE_2, EmailTemplateFileConstant.TEMPLATE_2);

    public final Integer templateNumber;
    public String baseSubject;
    public final String templateFileName;

    EmailTemplate(Integer templateNumber, String baseSubject, String templateFileName) {
        this.templateNumber = templateNumber;
        this.baseSubject = baseSubject;
        this.templateFileName = templateFileName;
    }
}
