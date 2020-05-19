package com.cafecoder.tistory.files;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

public class xlsxProcesser {
    private List<FileInputStream> fileInputStreams;
    private List<HSSFWorkbook> workbooks;

    public xlsxProcesser (List<File> files) {
        this.fileInputStreams = new ArrayList<>();
        this.workbooks = new ArrayList<>();

        for(File file : files) {
            try {
                this.fileInputStreams.add(new FileInputStream(file));
            }
            catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }

        for(FileInputStream fis : fileInputStreams) {
            try {
                this.workbooks.add(new HSSFWorkbook(fis));
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void readXlsx () {

    }
}
