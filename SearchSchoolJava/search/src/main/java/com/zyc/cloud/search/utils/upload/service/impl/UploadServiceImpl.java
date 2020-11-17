package com.zyc.cloud.search.utils.upload.service.impl;

import com.zyc.cloud.search.utils.ResultUtil;
import com.zyc.cloud.search.utils.StatusCode;
import com.zyc.cloud.search.utils.upload.service.UploadService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;

/**
 * @author yofu | @create 2020-11-13 14:03
 * @program
 * @description
 **/
@Service
public class UploadServiceImpl implements UploadService {


    @Override
    public String upload(MultipartFile mFile) throws Exception {

        if (mFile == null) {
            throw new Exception("文件不能为空！");
        }
        String path = "";
        try {
            InputStream in = mFile.getInputStream();
            byte[] buffer = new byte[1024];
            int len = 0;

            //文件保存的路径
            path = "E:/tmp/demo/"+System.currentTimeMillis()+"/"+ mFile.getOriginalFilename();

            File file = new File(path);
            File fileParent = file.getParentFile();
            if (!fileParent.exists()) {
                fileParent.mkdirs();
            }
            OutputStream out = new FileOutputStream(path);
            while ((len = in.read(buffer)) != -1) {
                out.write(buffer, 0, len);
            }
            out.close();
            in.close();
        } catch (Exception e) {
            System.out.println("----------" + path + "文件上传失败————————");
            e.printStackTrace();
        }
        return path;
    }
}
