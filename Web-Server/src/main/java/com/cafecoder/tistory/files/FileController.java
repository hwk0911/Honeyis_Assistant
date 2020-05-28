package com.cafecoder.tistory.files;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;
import java.util.stream.Collectors;

@RestController
public class FileController {
    private List<MultipartFile> multipartFiles;
    private List<List<String>> dataList;

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
        //this.sortList(0, dataList.size());
        List<OrderData> orderList = new ArrayList<>();


        for(List<String> data : dataList) {
            orderList.add(new OrderData(data));
        }

        for(OrderData orderData : orderList) {
            System.out.println(orderData.toString());
        }
    }

    public List<MultipartFile> getMultipartFiles () {
        return this.multipartFiles;
    }

    private void sortList (int start, int end) {
        int left = start;
        int right = end;
        String pivot = this.dataList.get((start + end) / 2).get(0);

        do {
            while (this.dataList.get(left).get(0).compareTo(pivot) != 1 && left < end) {
                ++left;
            }
            while (this.dataList.get(right).get(0).compareTo(pivot) != -1 && right > start) {
                --right;
            }

            if(left <= right) {
                List<String> tempList = this.dataList.get(left);
                this.dataList.set(left, this.dataList.get(right));
                this.dataList.set(right, tempList);
                ++left;
                --right;
            }
        }while (left <= right);

        if(left < end) {
            this.sortList(left, end);
        }
        if(right > start) {
            this.sortList(start, right);
        }
    }
}
