package com.example.springjasperreport.service.impl;

import com.example.springjasperreport.constant.Constant;
import com.example.springjasperreport.constant.PdfExportingConstant;
import com.example.springjasperreport.dto.DataBean;
import com.example.springjasperreport.service.ReportService;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.*;

@Service
public class ReportServiceImpl implements ReportService {

    private ArrayList<DataBean> getDataBeanList() {
        ArrayList<DataBean> dataBeanList = new ArrayList<DataBean>();

        dataBeanList.add(produce("Manisha", "In publishing and graphic design, Lorem ipsum is a placeholder text commonly used to demonstrate the visual form of a document or a typeface without relying on meaningful content. Lorem ipsum may be used as a placeholder before final c"));
        dataBeanList.add(produce("Dennis Ritchie", "USA"));
        dataBeanList.add(produce("V.Anand", "India"));
        dataBeanList.add(produce("Shrinath", "California"));
        dataBeanList.add(produce("Manisha", "In publishing and graphic design, Lorem ipsum is a placeholder text commonly used to demonstrate the visual form of a document or a typeface without relying on meaningful content. Lorem ipsum may be used as a placeholder before final c"));
        dataBeanList.add(produce("Dennis Ritchie", "USA"));
        dataBeanList.add(produce("V.Anand", "India"));
        dataBeanList.add(produce("Shrinath", "California"));
        dataBeanList.add(produce("Manisha", "In publishing and graphic design, Lorem ipsum is a placeholder text commonly used to demonstrate the visual form of a document or a typeface without relying on meaningful content. Lorem ipsum may be used as a placeholder before final c"));
        dataBeanList.add(produce("Dennis Ritchie", "USA"));
        dataBeanList.add(produce("V.Anand", "India"));
        dataBeanList.add(produce("Shrinath", "California"));
        dataBeanList.add(produce("Manisha", "In publishing and graphic design, Lorem ipsum is a placeholder text commonly used to demonstrate the visual form of a document or a typeface without relying on meaningful content. Lorem ipsum may be used as a placeholder before final c"));
        dataBeanList.add(produce("Dennis Ritchie", "USA"));
        dataBeanList.add(produce("V.Anand", "India"));
        dataBeanList.add(produce("Shrinath", "California"));
        dataBeanList.add(produce("Manisha", "In publishing and graphic design, Lorem ipsum is a placeholder text commonly used to demonstrate the visual form of a document or a typeface without relying on meaningful content. Lorem ipsum may be used as a placeholder before final c"));
        dataBeanList.add(produce("Dennis Ritchie", "USA"));
        dataBeanList.add(produce("V.Anand", "India"));
        dataBeanList.add(produce("Shrinath", "California"));
        dataBeanList.add(produce("Manisha", "In publishing and graphic design, Lorem ipsum is a placeholder text commonly used to demonstrate the visual form of a document or a typeface without relying on meaningful content. Lorem ipsum may be used as a placeholder before final c"));
        dataBeanList.add(produce("Dennis Ritchie", "USA"));
        dataBeanList.add(produce("V.Anand", "India"));
        dataBeanList.add(produce("Shrinath", "California"));
        dataBeanList.add(produce("Manisha", "In publishing and graphic design, Lorem ipsum is a placeholder text commonly used to demonstrate the visual form of a document or a typeface without relying on meaningful content. Lorem ipsum may be used as a placeholder before final c"));
        dataBeanList.add(produce("Dennis Ritchie", "USA"));
        dataBeanList.add(produce("V.Anand", "India"));
        dataBeanList.add(produce("Shrinath", "California"));

        return dataBeanList;
    }

    /**
     * This method returns a DataBean object,
     * with name and country set in it.
     */
    private DataBean produce(String name, String country) {
        DataBean dataBean = new DataBean();
        dataBean.setName(name);
        dataBean.setCountry(country);
        return dataBean;
    }

    @Override
    public byte[] exportPdf() throws JRException, IOException {
        // tìm kiếm file report
        JasperReport jasperReportFistPage = JasperCompileManager.compileReport(new
                FileSystemResource("src/main/resources/templates/jasper_report_template.jrxml").getInputStream());

        Map<String, Object> parameters = new HashMap<>();
        JRBeanCollectionDataSource itemsJRBean = new JRBeanCollectionDataSource(getDataBeanList());

        parameters.put("test","Just to test");
        parameters.put("exportedBy","admin");
        parameters.put("exportDate", PdfExportingConstant.FORMAT_DATE_REPORT.format(new Date()));
        parameters.put("dataBean",itemsJRBean);

        JasperPrint jasperPrintFistPage = JasperFillManager.fillReport(jasperReportFistPage, parameters, new JREmptyDataSource(1));

        //Throw the JasperPrint Objects in a list
        List<JasperPrint> jasperPrintList = new ArrayList<>();
        jasperPrintList.add(jasperPrintFistPage);

        JRPdfExporter exporter = new JRPdfExporter();
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        exporter.setExporterInput(SimpleExporterInput.getInstance(jasperPrintList));
        exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(byteArrayOutputStream));
        exporter.exportReport();

        return byteArrayOutputStream.toByteArray();
    }

}
