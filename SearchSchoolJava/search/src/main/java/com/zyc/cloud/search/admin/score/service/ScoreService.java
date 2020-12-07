package com.zyc.cloud.search.admin.score.service;

import com.zyc.cloud.search.admin.score.model.ScoreDo;
import com.zyc.cloud.search.utils.PageResult;

import javax.xml.transform.Result;
import java.util.HashMap;
import java.util.List;

/**
 * @author ZengYc
 * @Date 2020/11/5 21:39
 */
public interface ScoreService {
    /**
     * 新增
     */
    boolean add(ScoreDo scoreDo);

    /**
     * 根据id修改
     */
    boolean updateById(ScoreDo scoreDo);

    /**
     * 根据ID查询
     */
    ScoreDo findById(String id);

    /**
     * 查询所有
     */
    List<ScoreDo> findAll();

    /**
     * 条件查询
     */
    List<ScoreDo> findList(ScoreDo scoreDo);

    /**
     * 根据ID删除
     */
    void delete(String id);

    /**
     * 分页+条件搜索
     */
    PageResult<ScoreDo> findPage(ScoreDo scoreDo, int page, int size);

    /**
     * 多个专业一个学校的echarts
     */
    List<HashMap<String,Object>> scoreByColleges(HashMap<String,Object> map);
}
