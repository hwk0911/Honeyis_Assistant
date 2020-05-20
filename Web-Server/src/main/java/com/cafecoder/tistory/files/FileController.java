package com.cafecoder.tistory.files;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
public class FileController {
    @PostMapping("/api/va/filesup")
    public void getFiles (@RequestParam(value = "files") List<MultipartFile> multipartFiles) {
        for(MultipartFile multiFile : multipartFiles) {

        }
    }
}
