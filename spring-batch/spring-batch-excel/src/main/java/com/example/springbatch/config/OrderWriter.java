package com.example.springbatch.config;

import com.example.springbatch.model.Customers;
import org.apache.poi.hssf.usermodel.HSSFPalette;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.CellUtil;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.ItemStreamException;
import org.springframework.batch.item.ItemStreamWriter;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.WritableResource;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.util.List;

@Configuration
public class OrderWriter implements ItemStreamWriter<Customers> {
    private HSSFWorkbook wb;

    private WritableResource resource;

    private int row;

    @Override
    public void open(ExecutionContext executionContext) throws ItemStreamException {
        wb = new HSSFWorkbook();
        HSSFPalette palette = wb.getCustomPalette();
        HSSFSheet s = wb.createSheet();

        resource = new FileSystemResource("output.xlsx");
        row = 0;
        createTitleRow(s, palette);
        createHeaderRow(s);
    }

    private void createTitleRow(HSSFSheet s, HSSFPalette palette) {
        HSSFColor redish = palette.findSimilarColor((byte) 0xE6, (byte) 0x50, (byte) 0x32);
        palette.setColorAtIndex(redish.getIndex(), (byte) 0xE6, (byte) 0x50, (byte) 0x32);

        CellStyle headerStyle = wb.createCellStyle();
        headerStyle.setWrapText(true);
        headerStyle.setAlignment(HorizontalAlignment.CENTER);
        headerStyle.setFillForegroundColor(redish.getIndex());
        headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        headerStyle.setBorderTop(BorderStyle.THIN);
        headerStyle.setBorderBottom(BorderStyle.THIN);
        headerStyle.setBorderLeft(BorderStyle.THIN);
        headerStyle.setBorderRight(BorderStyle.THIN);

        HSSFRow r = s.createRow(row);

        Cell c = r.createCell(0);
        c.setCellValue("Internal Use Only");
        r.createCell(1).setCellStyle(headerStyle);
        r.createCell(2).setCellStyle(headerStyle);
        s.addMergedRegion(new CellRangeAddress(0, 0, 0, 3));
        c.setCellStyle(headerStyle);

        CellUtil.setAlignment(c, HorizontalAlignment.CENTER);

        row++;
    }

    private void createHeaderRow(HSSFSheet s) {
        CellStyle cs = wb.createCellStyle();
        cs.setWrapText(true);
        cs.setAlignment(HorizontalAlignment.LEFT);

        HSSFRow r = s.createRow(row);
        r.setRowStyle(cs);

        Cell c = r.createCell(0);
        c.setCellValue("Author");
        s.setColumnWidth(0, poiWidth(18.0));
        c = r.createCell(1);
        c.setCellValue("Book Name");
        s.setColumnWidth(1, poiWidth(24.0));
        c = r.createCell(2);
        c.setCellValue("ISBN");
        s.setColumnWidth(2, poiWidth(18.0));
        c = r.createCell(3);
        c.setCellValue("Price");
        s.setColumnWidth(3, poiWidth(18.0));

        row++;
    }

    private int poiWidth(double width) {
        return (int) Math.round(width * 256 + 200);
    }

    @Override
    public void update(ExecutionContext executionContext) throws ItemStreamException {

    }

    @Override
    public void close() throws ItemStreamException {
        if (wb == null) {
            return;
        }
        try (BufferedOutputStream bos = new BufferedOutputStream(resource.getOutputStream())) {
            wb.write(bos);
            bos.flush();
            wb.close();
        } catch (IOException ex) {
            throw new ItemStreamException("Error writing to output file", ex);
        }
        row = 0;
    }

    @Override
    public void write(List<? extends Customers> items) throws Exception {
        HSSFSheet s = wb.getSheetAt(0);

        for (Customers o : items) {
            Row r = s.createRow(row++);
            Cell c = r.createCell(0);
            c.setCellValue(o.getId());

        }
    }
}
