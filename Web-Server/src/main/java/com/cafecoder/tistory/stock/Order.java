package com.cafecoder.tistory.stock;

import java.util.HashMap;

public class Order {
    private String productName;
    private HashMap<String, Integer> option;

    public Order(String productName, String option, Integer amount) {
        this.productName = productName;
        this.option = new HashMap<>();;

        this.option.put(option, amount);
    }

    public void addOption (String option, Integer amount) {
        if(this.option.containsKey(option)) {
            this.option.put(option, this.option.get(option) + amount);
        }
        else {
            this.option.put(option, amount);
        }
    }

    public String getProductName () {
        return this.productName;
    }
}
