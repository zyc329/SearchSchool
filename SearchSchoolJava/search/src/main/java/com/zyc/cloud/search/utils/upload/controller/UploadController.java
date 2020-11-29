package com.zyc.cloud.search.utils.upload.controller;

import com.zyc.cloud.search.admin.school.model.PicDo;
import com.zyc.cloud.search.admin.school.service.PicService;
import com.zyc.cloud.search.utils.ResultUtil;
import com.zyc.cloud.search.utils.StatusCode;
import com.zyc.cloud.search.utils.upload.service.UploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author yofu | @create 2020-11-13 13:57
 * @program
 * @description
 **/
@RestController
public class UploadController {

    @Autowired
    private UploadService uploadService;

    @Autowired
    private PicService picService;


    @RequestMapping("/upload")
    public ResultUtil upload(MultipartFile file, String type) throws Exception {
        String path = uploadService.upload(file, type);
        return new ResultUtil(true, StatusCode.OK, "图片上传成功", path);
    }

    @PostMapping("/deletePic")
    public ResultUtil deletePic(@RequestParam String deletePicId){
        String message = "";
        PicDo picDo = picService.findById(deletePicId);
        picService.delete(deletePicId);
        try {
            File file = new File(picDo.getPicSrc());
            if (file.delete()){
                message="文件删除成功！";
            }else {
                message="文件删除失败！";
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        return new ResultUtil(true, StatusCode.OK, message);
    }

    @RequestMapping("/download")
    public void download(String picId, HttpServletRequest request, HttpServletResponse response) throws IOException {
        InputStream inStream = null;
        ServletOutputStream out = null;
        try {
            PicDo picDo = picService.findById(picId);
            String fileUrl = picDo.getPicSrc();
            inStream = new FileInputStream(fileUrl);
            response.setContentType("multipart/form-data");
            response.setHeader("Content-Disposition", "attachment;fileName=" + picDo.getPicName());
            out = response.getOutputStream();
            // 循环取出流中的数据
            byte[] b = new byte[1024 * 10];
            int len;

            while ((len = inStream.read(b)) > 0) {
                out.write(b, 0, len);
            }
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            out.close();
            inStream.close();
        }
    }
}
