package com.zyc.cloud.search.admin.comments.controller;

import com.zyc.cloud.search.admin.comments.model.CommentsDo;
import com.zyc.cloud.search.admin.comments.service.CommentsService;
import com.zyc.cloud.search.utils.PageResult;
import com.zyc.cloud.search.utils.ResultUtil;
import com.zyc.cloud.search.utils.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author ZengYc
 * @Date 2020/11/5 21:07
 */
@RestController
@RequestMapping(value = "/comments")
@CrossOrigin
public class CommentsController {
    @Autowired
    private CommentsService commentsService;

    /**
     * 条件查询
     */
    @PostMapping("commentsFindList")
    public ResultUtil<List<CommentsDo>> commentsFindList(@RequestBody CommentsDo commentsDo) {
        List<CommentsDo> texts = commentsService.findList(commentsDo);
        return new ResultUtil<List<CommentsDo>>(true, StatusCode.OK, "条件查询成功", texts);
    }

    /**
     * 新增
     */
    @PostMapping("commentsAdd")
    public ResultUtil commentsAdd(@RequestBody CommentsDo commentsDo) {
        CommentsDo item = new CommentsDo();
        commentsService.add(commentsDo);
        return new ResultUtil(true, StatusCode.OK, "新增成功！");
    }


    /**
     * 根据ID删除
     */
    @PostMapping("commentsDelete")
    public ResultUtil commentsDelete(@RequestParam(value = "commentsId") String commentsId) {
        commentsService.delete(commentsId);
        return new ResultUtil(true, StatusCode.OK, "删除成功！");
    }


}
