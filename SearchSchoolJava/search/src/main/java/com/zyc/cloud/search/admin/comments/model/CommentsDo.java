package com.zyc.cloud.search.admin.comments.model;

import cn.hutool.core.date.DateTime;
import com.zyc.cloud.search.utils.base.BaseDo;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * @author ZengYc
 * @Date 2020/11/5 21:17
 */
@Data
@Table(name = "tb_comments")
public class CommentsDo extends BaseDo {

    /*评论回复id*/
    @Id
    @Column(name = "comments_id")
    private String commentsId;

    /*评论的学校id*/
    @Column(name = "school_id")
    private String schoolId;

    /*被评论评论表的id*/
    @Column(name = "to_comments_id")
    private String toCommentsId;

    /*被评论人用户名*/
    @Column(name = "to_critics_name")
    private String toCriticsName;

    /*被评论人id*/
    @Column(name = "to_critics_id")
    private String toCriticsId;

    /*是否是评论*/
    @Column(name = "is_comments")
    private String isComments;

    /*评论的内容*/
    @Column(name = "comments")
    private String comments;

    /*评论状态*/
    @Column(name = "comments_status")
    private String commentsStatus;

    /*评论图片src*/
    @Column(name = "comments_pic")
    private String commentsPic;

//    /*评论时间*/
//    @Column(name = "comments_time")
//    private DateTime commentsTime;

    /*评论人用户名*/
    @Column(name = "critics_name")
    private String criticsName;
    /*评论人id*/
    @Column(name = "critics_id")
    private String criticsId;

}
