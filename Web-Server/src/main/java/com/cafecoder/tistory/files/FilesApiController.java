package com.cafecoder.tistory.files;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@RestController
public class FilesApiController {

    @RequestMapping(path = "/api/va/filesup", method = RequestMethod.POST,
    consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public void filesup (@RequestParam("files") List<MultipartFile> files) {
        List<File> convFiles = new ArrayList<>();
        for(int index = 0, size = files.size() ; index < size ; ++index) {
            MultipartFile tempFile = files.get(index);

            if(tempFile.getOriginalFilename().contains(".xlsx")) {
                convFiles.add(convMultiToFile(tempFile));
            }
            else {
                continue;
            }
        }
        //받은 xlsx 파일 처리 할 함수 
    }

    public File convMultiToFile (MultipartFile multipartFile){

        File convFile = new File(multipartFile.getOriginalFilename());

        try {
            multipartFile.transferTo(convFile);
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        return convFile;
    }
}

/*
package com.cafecoder.tistory.user;

import com.cafecoder.tistory.service.UsersService;
import com.cafecoder.tistory.user.dto.UsersSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class UsersApiController {

    private final UsersService usersService;

    @PostMapping("/api/v1/users")
    public Long signup (@RequestBody UsersSaveRequestDto requestDto) {

        return usersService.save(requestDto);
    }
}
 */