package com.zyc.cloud.search.utils.upload.controller;

import com.zyc.cloud.search.utils.upload.service.UploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author yofu | @create 2020-11-13 13:57
 * @program
 * @description
 **/
@RestController
public class UploadController {

    @Autowired
    private UploadService uploadService;


    @RequestMapping("/upload")
    public String upload(MultipartFile file) throws Exception {
        uploadService.upload(file);
        return "上传成功";
    }


}
