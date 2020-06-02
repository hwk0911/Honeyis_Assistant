package com.cafecoder.tistory.files;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;

@RestController
public class FileApiController {
    private List<MultipartFile> multipartFiles;
    private List<List<String>> dataList;
    private HashMap<String, HashMap<String, Integer>> orderHashMap;

    @PostMapping("/api/va/orderfilesup")
    public void getFiles (@RequestParam(value = "files") List<MultipartFile> multipartFiles) {
        this.multipartFiles = new ArrayList<>();

        for(MultipartFile file : multipartFiles) {
            if(file.getOriginalFilename().contains(".xlsx")) {
                this.multipartFiles.add(file);
            }
        }

        multipartFiles = null;

        XlsxProcessor xlsxProcessor = new XlsxProcessor(this.multipartFiles);

        this.dataList = xlsxProcessor.getDataList();
        List<OrderData> orderList = new ArrayList<>();

        for(List<String> data : dataList) {
            orderList.add(new OrderData(data));
        }

        Collections.sort(orderList, new Comparator<OrderData>() {
            @Override
            public int compare(OrderData o1, OrderData o2) {
                return (int)(o1.getDateValue() - o2.getDateValue());
            }
        });

        this.setOrderHashMap(orderList);

        Iterator<String> itr = this.orderHashMap.keySet().iterator();

        while(itr.hasNext()) {
            String productName = itr.next();
            HashMap<String, Integer> optionMap = this.orderHashMap.get(productName);

            System.out.println(productName);
            Iterator<String> optionItr = optionMap.keySet().iterator();

            while(optionItr.hasNext()) {
                String color = optionItr.next();

                System.out.println(color + "\t\t" + optionMap.get(color));
            }

            System.out.println();
        }
    }

    private void setOrderHashMap (List<OrderData> orderDataList) {
        HashMap<String, HashMap<String, Integer>> retHashMap = new HashMap<>();
        HashMap<String, Integer> optionMap = new HashMap<>();

        for(OrderData orderData : orderDataList) {
            String productName = orderData.getProductName();

            if(retHashMap.containsKey(productName)) {
                optionMap = retHashMap.get(productName);
            }
            else {
                optionMap = new HashMap<>();
            }

            for(String color : orderData.getColor()) {
                int amount = orderData.getAmount();

                if(optionMap.containsKey(color)) {
                    optionMap.put(color, optionMap.get(color) + amount);
                }
                else {
                    optionMap.put(color, amount);
                }
            }

            retHashMap.put(productName, optionMap);
        }

        this.orderHashMap = retHashMap;
    }
}
