package com.zyc.cloud.search.utils.upload.controller;

import com.zyc.cloud.search.utils.ResultUtil;
import com.zyc.cloud.search.utils.StatusCode;
import com.zyc.cloud.search.utils.upload.service.UploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
    public ResultUtil upload(MultipartFile file) throws Exception {
        String path = uploadService.upload(file);
        return new ResultUtil(true, StatusCode.OK, "条件查询成功", path);

    }

    @RequestMapping("/download")
    public void download(String id, HttpServletRequest request, HttpServletResponse response){

    }


}
