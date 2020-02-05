package com.rachnicrice.spordering.utils;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.core.io.ByteArrayResource;

import java.io.FileOutputStream;
import java.io.IOException;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

//Resource: https://www.codejava.net/coding/how-to-write-excel-files-in-java-using-apache-poi
//Resource: https://stackoverflow.com/questions/52078128/spring-boot-controller-export-an-excel
public class ExcelConverter {

    public static ByteArrayResource export(String filename) throws IOException {
        byte[] bytes = new byte[1024];
        try (Workbook workbook = (Workbook) generateExcel()) {
            FileOutputStream fos = write(workbook, filename);
            fos.write(bytes);
            fos.flush();
            fos.close();
        }

        return new ByteArrayResource(bytes);
    }

    //Change from AutoCloseable to Workbook if this does not work
    //Had to change to AutoCloseable due to version changes from Apache 3.9 to 4.0
    public static AutoCloseable generateExcel() {
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Order");

        //create columns and rows
        Object[][] bookData = {
                {"Head First Java", "Kathy Serria", 79},
                {"Effective Java", "Joshua Bloch", 36},
                {"Clean Code", "Robert martin", 42},
                {"Thinking in Java", "Bruce Eckel", 35},
        };

        int rowCount = 0;

        for (Object[] aBook : bookData) {
            Row row = sheet.createRow(++rowCount);
            int columnCount = 0;

            for (Object field : aBook) {
                Cell cell = row.createCell(++columnCount);
                if (field instanceof String) {
                    cell.setCellValue((String) field);
                } else if (field instanceof Integer) {
                    cell.setCellValue((Integer) field);
                }
            }
        }

        return (AutoCloseable) workbook;
    }

    private static FileOutputStream write(final Workbook workbook, final String filename) throws IOException {
        FileOutputStream fos = new FileOutputStream(filename);
        workbook.write(fos);
        fos.close();
        return fos;
    }

}
