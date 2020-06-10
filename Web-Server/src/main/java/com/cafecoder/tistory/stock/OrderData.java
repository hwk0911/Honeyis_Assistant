package com.cafecoder.tistory.stock;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class OrderData {
    private String date;
    private Long dateValue;
    private String orderNumber;
    private String productName;
    private Integer price;
    private String optionData;
    private List<String> color;
    private String size;
    private int amount;
    private String customer;
    private String customerPh;
    private String receiver;
    private String receiverPh;
    private String zipCode;
    private String address;
    private String note;

    public OrderData(List<String> data) {
        this.date = data.get(0);
        this.orderNumber = data.get(1);
        this.productName = data.get(2);
        this.price = Integer.parseInt(data.get(3).split("\\.")[0]);

        this.optionData = data.get(4);
        this.color = new ArrayList<>();
        for(String temp : optionData.split("/")) {
            if(!temp.contains("1개") && !temp.contains("단품")) {
                if(!temp.toUpperCase().equals("FREE")) {
                    this.color.add(temp);
                }
                else {
                    this.size = temp;
                }
            }
        }

        if(this.size == null) {
            this.size = "free";
        }

        this.amount = Integer.parseInt(data.get(5).split("\\.")[0]);
        this.customer = data.get(6);
        this.customerPh = data.get(7);
        this.receiver = data.get(8);
        this.receiverPh = data.get(9);
        this.zipCode = data.get(10);
        this.address = data.get(11);
        this.note = data.get(12);
        this.dateValue = this.getDateValue();
    }

    public Long getDateValue () {
        Long dateTime = 0L;

        try {
            Date date = new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(this.date);
            dateTime = date.getTime();
        }
        catch (ParseException e) {
            e.getStackTrace();
        }

        return dateTime / 1000;
    }
}
