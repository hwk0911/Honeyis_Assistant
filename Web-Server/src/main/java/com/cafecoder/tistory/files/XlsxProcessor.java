package com.cafecoder.tistory.files;

import com.cafecoder.tistory.stock.Order;
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
    private List<List<String>> dataList;
    private List<Order> orderDataList;
    private List<Integer> columns;
    private List<Integer> orderColumns;

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

        this.setColumns();
        this.dataProcessor();
        this.setOrderList();
    }

    private void setColumns () {
        this.columns = new ArrayList<>();
        this.orderColumns = new ArrayList<>();

        for(XSSFWorkbook workbook : workbooks) {
            int index = 0;

            XSSFSheet sheet = workbook.getSheetAt(workbook.getNumberOfSheets() - 1);

            XSSFRow row = sheet.getRow(index);

            int cells = row.getPhysicalNumberOfCells();
            for(int column = 0; column <= cells ; ++column) {
                XSSFCell cell = row.getCell(column);

                if(cell == null) {
                    continue;
                }
                else {
                    switch (cell.toString()) {
                        case "상품명":
                        case "옵션 정보":
                        case "수량":
                            this.orderColumns.add(column);
                        case "결제일":
                        case "주문번호":
                        case "판매가":
                        case "주문자명":
                        case "연락처":
                        case "수취인명":
                        case "수취인 연락처":
                        case "우편번호":
                        case "배송지 주소":
                        case "배송 메모":
                            this.columns.add(column);
                            break;
                        default:
                            continue;
                    }
                }
            }
        }
    }

    public void dataProcessor () {
        this.dataList = new ArrayList<>();

        for(XSSFWorkbook workbook : workbooks) {
            XSSFSheet sheet = workbook.getSheetAt(workbook.getNumberOfSheets() - 1);

            int maxRow = sheet.getPhysicalNumberOfRows();

            for(int index = 1 ; index < maxRow ; ++index) {
                List<String> tempData = new ArrayList<>();
                XSSFRow row = sheet.getRow(index);

                for(int cellNum : this.columns) {
                    XSSFCell cell = row.getCell(cellNum);
                    tempData.add(cell.toString());
                }
                this.dataList.add(tempData);
            }
        }
    }

    public void setOrderList () {
        this.orderDataList = new ArrayList<>();

        for(XSSFWorkbook workbook : workbooks) {
            XSSFSheet sheet = workbook.getSheetAt(workbook.getNumberOfSheets() - 1);

            int maxRow = sheet.getPhysicalNumberOfRows();

            for(int index = 1 ; index < maxRow ; ++index) {
                List<String> tempData = new ArrayList<>();
                XSSFRow row = sheet.getRow(index);

                for(int cellNum : this.orderColumns) {
                    XSSFCell cell = row.getCell(cellNum);
                    tempData.add(cell.toString());
                }
                addOrderList(tempData);
            }
        }
    }

    public void addOrderList (List<String> data) {
        for(int index = 0, size = this.orderDataList.size() ; index < size ; ++index) {
            if(this.orderDataList.get(index).getProductName().contains(data.get(0))) {
                orderDataList.get(index).addOption(data.get(1), Integer.parseInt(data.get(2).split("\\.")[0]));
                return;
            }
        }

        this.orderDataList.add(new Order(data.get(0), data.get(1), Integer.parseInt(data.get(2).split("\\.")[0])));
    }

    public List<List<String>> getDataList () {
        return this.dataList;
    }

    public List<Order> getOrderDataList () {
        return this.orderDataList;
    }
}
