package com.zyc.cloud.search.admin.professional.service;

import com.zyc.cloud.search.admin.professional.model.ProfessDo;
import com.zyc.cloud.search.utils.PageResult;

import java.util.List;

/**
 * @author ZengYc
 * @Date 2020/11/5 21:39
 */
public interface ProfessService {
    /**
     * 新增
     */
    void add(ProfessDo professDo);

    /**
     * 根据id修改
     */
    void updateById(ProfessDo professDo);

    /**
     * 根据ID查询
     */
    ProfessDo findById(String id);

    /**
     * 查询所有
     */
    List<ProfessDo> findAll();

    /**
     * 条件查询
     */
    List<ProfessDo> findList(ProfessDo professDo);

    /**
     * 根据ID删除
     */
    void delete(String id);

    /**
     * 分页+条件搜索
     */
    PageResult<ProfessDo> findPage(ProfessDo professDo, int page, int size);

}
