package com.zyc.cloud.search.admin.comments.service;

import com.zyc.cloud.search.admin.college.model.CollegeDo;
import com.zyc.cloud.search.admin.comments.model.CommentsDo;
import com.zyc.cloud.search.utils.PageResult;

import java.util.List;

/**
 * @author ZengYc
 * @Date 2020/11/5 21:39
 */
public interface CommentsService {
    /**
     * 新增
     */
    void add(CommentsDo commentsDo);



    /**
     * 根据ID删除
     */
    void delete(String id);
    /**
     * 条件查询
     */
    List<CommentsDo> findList(CommentsDo commentsDo);
}
