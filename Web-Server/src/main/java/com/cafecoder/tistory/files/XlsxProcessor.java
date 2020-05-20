package com.cafecoder.tistory.files;


import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class XlsxProcessor {
    private List<XSSFWorkbook> workbooks;

    public XlsxProcessor(List<MultipartFile> files) {
        this.workbooks = new ArrayList<>();

        System.out.println(files.get(0).getSize());

        for(MultipartFile file : files) {
            try {
                XSSFWorkbook workbook = new XSSFWorkbook(file.getInputStream());
                this.workbooks.add(workbook);
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }

        this.printXlsx();
    }

    public void printXlsx () {
        for(XSSFWorkbook workbook : workbooks) {
            int index = 0;
            int column = 0;

            XSSFSheet sheet = workbook.getSheetAt(0);

            int maxRow = sheet.getPhysicalNumberOfRows();

            for(index = 0; index < maxRow ; ++index) {
                XSSFRow row = sheet.getRow(index);

                try {
                    int cells = row.getPhysicalNumberOfCells();

                    for(column = 0 ; column <= cells ; ++column) {
                        XSSFCell cell = row.getCell(column);

                        if(cell == null) {
                            continue;
                        }
                        else {
                            System.out.print(cell.toString() + "\t");
                        }
                    }
                }
                catch (NullPointerException e) {
                    System.out.println(e.getMessage());
                    continue;
                }

                System.out.println();
            }
        }
    }
}
