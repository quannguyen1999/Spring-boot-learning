package com.example.springjasperreport.controller;

import com.example.springjasperreport.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ReportController {

    @Autowired
    ReportService reportService;

    @GetMapping(value = "demo-report", produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<byte[]> getReport() {
        try {
            return new ResponseEntity<byte[]>(reportService.exportPdf(), HttpStatus.OK);
        }
        catch(Exception e) {
            e.printStackTrace();
            return new ResponseEntity<byte[]>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
