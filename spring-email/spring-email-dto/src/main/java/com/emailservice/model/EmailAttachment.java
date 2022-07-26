package com.emailservice.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.core.io.FileSystemResource;

@Getter
@Setter
public class EmailAttachment {

    private String fileName;

    private FileSystemResource file;

}
