package com.zyc.cloud.search.admin.school.service;

import com.zyc.cloud.search.admin.school.model.PicDo;

/**
 * @author ZengYc
 * @Date 2020/11/18 0:24
 */
public interface PicService {
    /**
     * 新增
     */
    void add(PicDo picDo);

    /**
     * 根据ID查询
     * @return
     */
    PicDo findById(String id);
}
