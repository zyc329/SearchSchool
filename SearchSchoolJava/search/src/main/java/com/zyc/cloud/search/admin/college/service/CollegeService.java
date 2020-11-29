package com.zyc.cloud.search.admin.college.service;

import com.zyc.cloud.search.admin.college.model.CollegeDo;
import com.zyc.cloud.search.utils.PageResult;

import java.util.List;

/**
 * @author ZengYc
 * @Date 2020/11/5 21:39
 */
public interface CollegeService {
    /**
     * 新增
     */
    void add(CollegeDo collegeDo);

    /**
     * 根据id修改
     */
    void updateById(CollegeDo collegeDo);

    /**
     * 根据ID查询
     */
    CollegeDo findById(String id);

    /**
     * 查询所有
     */
    List<CollegeDo> findAll();

    /**
     * 条件查询
     */
    List<CollegeDo> findList(CollegeDo collegeDo);

    /**
     * 根据ID删除
     */
    void delete(String id);

    /**
     * 分页+条件搜索
     */
    PageResult<CollegeDo> findPage(CollegeDo collegeDo, int page, int size);

}
