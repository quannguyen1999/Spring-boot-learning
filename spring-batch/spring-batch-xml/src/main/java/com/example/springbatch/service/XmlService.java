package com.example.springbatch.service;

import com.example.springbatch.enums.TypeXml;
import org.springframework.batch.core.Job;

public interface XmlService {
    public Job runJobBaseOnType(TypeXml typeXml);
}
