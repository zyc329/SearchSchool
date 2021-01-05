package com.zyc.cloud.search.admin.comments.mapper;

import com.zyc.cloud.search.admin.comments.model.CommentsDo;
import com.zyc.cloud.search.utils.base.MyBaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

/**
 * @author ZengYc
 * @Date 2020/11/5 21:35
 */
@Mapper
@Repository
@Component
public interface CommentsMapper extends MyBaseMapper<CommentsDo> {


}
