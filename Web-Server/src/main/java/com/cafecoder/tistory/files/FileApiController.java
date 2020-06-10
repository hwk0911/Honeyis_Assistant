package com.cafecoder.tistory.files;

import com.cafecoder.tistory.stock.OrderData;
import com.cafecoder.tistory.user.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;

@RequiredArgsConstructor
@Controller
public class FileApiController {
    private List<MultipartFile> multipartFiles;
    private List<List<String>> dataList;
    private List<OrderData> sendList;

    private final UsersRepository usersRepository;

    @RequestMapping(value = "/api/v1/sendfilesup", method = RequestMethod.POST)
    public String getFiles (@RequestParam(value = "files") List<MultipartFile> multipartFiles, Model model) {
        this.multipartFiles = new ArrayList<>();

        for(MultipartFile file : multipartFiles) {
            if(file.getOriginalFilename().contains(".xlsx")) {
                this.multipartFiles.add(file);
            }
        }

        multipartFiles = null;

        XlsxProcessor xlsxProcessor = new XlsxProcessor(this.multipartFiles);

        this.dataList = xlsxProcessor.getDataList();

        List<OrderData> sendList = new ArrayList<>();

        for(List<String> data : dataList) {
            sendList.add(new OrderData(data));
        }

        Collections.sort(sendList, new Comparator<OrderData>() {
            @Override
            public int compare(OrderData o1, OrderData o2) {
                return (int)(o1.getDateValue() - o2.getDateValue());
            }
        });

        this.sendList = sendList;

        return "sendList";
    }

    @GetMapping("/users/sendList")
    public String sendList (Model model) {
        model.addAttribute("sendList", this.sendList);

        return "sendList";
    }

    @GetMapping("/users/orderList")
    public String orderList (Model model) {
        model.addAttribute("orderList", this.sendList);

        return "orderList";
    }
}
