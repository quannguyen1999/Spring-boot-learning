package com.example.springjasperreport.service;

import net.sf.jasperreports.engine.JRException;
import java.io.IOException;

public interface ReportService {

    byte[] exportPdf() throws JRException, IOException;

}
