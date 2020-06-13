package com.cafecoder.tistory.stock;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Order {
    private int id;
    private String productName;
    private HashMap<String, Integer> option;
    private Set<HashMap.Entry<String,Integer>> optionEntry;
    private String client = "";

    public Order(String productName, String client, HashMap<String, Integer> option) {
        this.productName = productName;
        this.client = client;
        this.optionEntry = option.entrySet();
    }

    public Order(int id, String productName, String option, Integer amount) {
        this.id = id;
        this.productName = productName;
        this.option = new HashMap<>();


        String[] tempOption = option.split("/");

        this.addOption(option, amount);
    }

    public void addOption (String option, Integer amount) {
        String[] tempOption = option.split("/");

        for(String opt : tempOption) {
            if(opt.contains("1개") || opt.toUpperCase().equals("FREE")) {
                continue;
            }
            else {
                if(this.option.containsKey(opt)) {
                    this.option.put(opt, this.option.get(opt) + amount);
                }
                else {
                    this.option.put(opt, amount);
                }
            }
        }
    }
    public void setClient (String client) {
        this.client = client;
    }

    public String getProductName () {
        return this.productName;
    }

    public void updateSet () {
        this.optionEntry = this.option.entrySet();
    }

    public String getClient () {
        return this.client;
    }
}
