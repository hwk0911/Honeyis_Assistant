package com.cafecoder.tistory.files;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class OrderData {
    private String date;
    private Long dateValue;
    private String productName;
    private List<String> color;
    private String size;
    private int amount;

    public OrderData(List<String> data) {
        this.date = data.get(0);
        this.productName = data.get(1);

        String option = data.get(2);
        this.color = new ArrayList<>();
        for(String temp : option.split("/")) {
            if(!temp.contains("1개")) {
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

        this.amount = Integer.parseInt(data.get(3).split("\\.")[0]);
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

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.date + " ");
        sb.append(this.productName + " ");
        for(String temp : this.color) {
            sb.append(temp + " ");
        }
        sb.append(this.size + " ");
        sb.append(this.amount);

        return sb.toString();
    }
}
