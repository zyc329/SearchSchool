package com.zyc.cloud.search.admin.score.controller;

import com.zyc.cloud.search.admin.score.model.ScoreDo;
import com.zyc.cloud.search.admin.score.service.ScoreService;
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
@RequestMapping(value = "/score")
@CrossOrigin
public class ScoreController {
    @Autowired
    private ScoreService scoreService;

    /**
     * 根据主键ID查询
     */
    @PostMapping("scoreFindId")
    public ResultUtil<ScoreDo> scoreFindId(@RequestBody String scoreId) {
        // 调用Service实现查询
        ScoreDo scoreDo = scoreService.findById(scoreId);
        return ResultUtil.success("查询成功", scoreDo);
    }

    /**
     * 条件查询
     */
    @PostMapping("scoreFindList")
    public ResultUtil<List<ScoreDo>> scoreFindList(@RequestBody ScoreDo scoreDo) {
        List<ScoreDo> texts = scoreService.findList(scoreDo);
        return new ResultUtil<List<ScoreDo>>(true, StatusCode.OK, "条件查询成功", texts);
    }

    /**
     * 查询所有
     */
    @PostMapping("scoreFindAll")
    public ResultUtil<List<ScoreDo>> scoreFindAll() {
        List<ScoreDo> scoreDos = scoreService.findAll();
        return new ResultUtil<List<ScoreDo>>(true, StatusCode.OK, "查询成功", scoreDos);
    }

    /**
     * 新增
     */
    @PostMapping("scoreAdd")
    public ResultUtil scoreAdd(@RequestBody ScoreDo scoreDo) {
        ScoreDo item = new ScoreDo();
        scoreService.add(scoreDo);
        return new ResultUtil(true, StatusCode.OK, "新增成功！");
    }

    /**
     * 修改
     */
    @PostMapping("scoreUpdate")
    public ResultUtil scoreUpdate(@RequestBody ScoreDo scoreDo) {
        scoreService.updateById(scoreDo);
        return new ResultUtil(true, StatusCode.OK, "修改成功！");
    }

    /**
     * 根据ID删除
     */
    @PostMapping("scoreDelete")
    public ResultUtil scoreDelete(@RequestParam(value = "scoreId") String scoreId) {
        scoreService.delete(scoreId);
        return new ResultUtil(true, StatusCode.OK, "删除成功！");
    }

    /**
     * 条件+分页查询实现
     */
    @PostMapping("/scoreFindPage")
    public ResultUtil<PageResult<ScoreDo>> scoreFindPage(@RequestBody(required = false) ScoreDo scoreDo,
                                                           @RequestParam(required = false, defaultValue = "1") int page,
                                                           @RequestParam(required = false, defaultValue = "10") int size){
        PageResult<ScoreDo> pageResult = scoreService.findPage(scoreDo,page,size);
        return new ResultUtil<PageResult<ScoreDo>>(true, StatusCode.OK, "分页查询成功",pageResult);
    }

}
