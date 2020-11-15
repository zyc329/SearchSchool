package com.zyc.cloud.search.utils.upload.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * @program:
 * @description:
 * @author: yofu | @create: 2020-11-13 14:01
 **/
public interface UploadService {
    /**
     * 文件上传
     * @param file
     */
    void upload(MultipartFile file) throws Exception;
}
