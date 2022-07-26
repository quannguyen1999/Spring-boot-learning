package com.emailservice.feign.client;

import com.emailservice.model.Email;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(value = "emailService", url = "${feign-client.email-service.url}")
public interface EmailServiceClient {

    @PostMapping(value = "/send")
    ResponseEntity<String> sendMail(@RequestBody Email email);

}
