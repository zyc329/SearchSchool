package com.zyc.cloud.search.utils.upload.controller;

import com.zyc.cloud.search.admin.school.model.PicDo;
import com.zyc.cloud.search.admin.school.service.PicService;
import com.zyc.cloud.search.utils.ResultUtil;
import com.zyc.cloud.search.utils.StatusCode;
import com.zyc.cloud.search.utils.upload.service.UploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
        return new ResultUtil(true, StatusCode.OK, "条件查询成功", path);

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
            response.setHeader("Content-Disposition","attachment;fileName="+picDo.getPicName());
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
