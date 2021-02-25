package com.zyc.cloud.search.admin.comments.service.impl;

import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.zyc.cloud.search.admin.comments.mapper.CommentsMapper;
import com.zyc.cloud.search.admin.comments.model.CommentsDo;
import com.zyc.cloud.search.admin.comments.service.CommentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * @author ZengYc
 * @Date 2020/11/5 21:39
 */
@Service
public class CommentsServiceImpl implements CommentsService {
    @Autowired
    private CommentsMapper commentsMapper;

    @Override
    public void add(CommentsDo commentsDo) {
        commentsDo.setCommentsId(IdUtil.objectId());
        commentsMapper.insertSelective(commentsDo);
    }


    @Override
    public void delete(String id) {
        commentsMapper.deleteByPrimaryKey(id);
    }

    @Override
    public List<CommentsDo> findList(CommentsDo commentsDo) {
        Example example = createExample(commentsDo);
        return commentsMapper.selectByExample(example);
    }

    public Example createExample(CommentsDo commentsDo) {
        Example example = new Example(CommentsDo.class);
        Example.Criteria criteria = example.createCriteria();
        if (ObjectUtil.isNotEmpty(commentsDo)) {
//            if (StrUtil.isNotEmpty(commentsDo.getCommentsStatus())) {
                criteria.andEqualTo("schoolId",commentsDo.getSchoolId());
//            }
        }
        return example;
    }
}
