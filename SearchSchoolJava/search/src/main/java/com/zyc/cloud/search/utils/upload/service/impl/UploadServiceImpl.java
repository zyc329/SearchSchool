package com.zyc.cloud.search.utils.upload.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.IdUtil;
import com.zyc.cloud.search.admin.school.model.PicDo;
import com.zyc.cloud.search.admin.school.service.PicService;
import com.zyc.cloud.search.utils.upload.service.UploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.HashMap;

/**
 * @author yofu | @create 2020-11-13 14:03
 * @program
 * @description
 **/
@Service
public class UploadServiceImpl implements UploadService {

    @Autowired
    private PicService picService;

    private final static String UPLOADURL = "E:\\tmp\\upload";

    @Override
    public String upload(MultipartFile mFile, String type) throws Exception {

        if (mFile == null) {
            throw new Exception("文件不能为空！");
        }

        String fileName = mFile.getOriginalFilename();
        String ext = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();

        String path = "";
        String filePath = "";
        try {
            InputStream in = mFile.getInputStream();
            byte[] buffer = new byte[1024];
            int len = 0;

            //文件保存的路径
            path = UPLOADURL + File.separator;

            File file = new File(path);
//            File fileParent = file.getParentFile();
//            if (!fileParent.exists()) {
//                fileParent.mkdirs();
//            }
            if (!file.exists()) {
                FileUtil.mkdir(file);
            }
            filePath = new StringBuilder(path).append(fileName.substring(0, fileName.lastIndexOf("."))).append(IdUtil.objectId()).append(".").append(ext).toString();
            OutputStream out = new FileOutputStream(filePath);
            while ((len = in.read(buffer)) != -1) {
                out.write(buffer, 0, len);
            }
            out.close();
            in.close();
        } catch (Exception e) {
            System.out.println("----------" + path + "文件上传失败————————");
            e.printStackTrace();
        }
        String picId = IdUtil.objectId();
        HashMap<String,Object> picMap = new HashMap<>();
        picMap.put("picId", picId);
        picMap.put("picName", fileName);
        picMap.put("picType", type);
        picMap.put("picSrc", filePath);
        PicDo picDo = BeanUtil.toBean(picMap, PicDo.class);
        picService.add(picDo);
        return picId;
    }
}
