package com.cafecoder.tistory.files;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@RestController
public class FileController {
    private List<MultipartFile> multipartFiles;

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

        List<String> dataList = xlsxProcessor.getDataList();
        Collections.sort(dataList);
        List<OrderData> orderList = new ArrayList<>();


        for(String data : xlsxProcessor.getDataList()) {
            orderList.add(new OrderData(data));
        }

        for(OrderData orderData : orderList) {
            System.out.println(orderData.toString());
        }
    }

    public List<MultipartFile> getMultipartFiles () {
        return this.multipartFiles;
    }
}
