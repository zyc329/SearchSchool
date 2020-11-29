package com.zyc.cloud.search.admin.teacher.service;

import com.zyc.cloud.search.admin.teacher.model.TeacherDo;
import com.zyc.cloud.search.utils.PageResult;

import java.util.List;

/**
 * @author ZengYc
 * @Date 2020/11/5 21:39
 */
public interface TeacherService {
    /**
     * 新增
     */
    void add(TeacherDo teacherDo);

    /**
     * 根据id修改
     */
    void updateById(TeacherDo teacherDo);

    /**
     * 根据ID查询
     */
    TeacherDo findById(String id);

    /**
     * 查询所有
     */
    List<TeacherDo> findAll();

    /**
     * 条件查询
     */
    List<TeacherDo> findList(TeacherDo teacherDo);

    /**
     * 根据ID删除
     */
    void delete(String id);

    /**
     * 分页+条件搜索
     */
    PageResult<TeacherDo> findPage(TeacherDo teacherDo, int page, int size);

}
