package com.zyc.cloud.search.admin.college.controller;

import com.zyc.cloud.search.admin.college.model.CollegeDo;
import com.zyc.cloud.search.admin.college.service.CollegeService;
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
@RequestMapping(value = "/college")
@CrossOrigin
public class CollegeController {
    @Autowired
    private CollegeService collegeService;

    /**
     * 根据主键ID查询
     */
    @PostMapping("collegeFindId")
    public ResultUtil<CollegeDo> collegeFindId(@RequestBody String collegeId) {
        // 调用Service实现查询
        CollegeDo collegeDo = collegeService.findById(collegeId);
        return ResultUtil.success("查询成功", collegeDo);
    }

    /**
     * 条件查询
     */
    @PostMapping("collegeFindList")
    public ResultUtil<List<CollegeDo>> collegeFindList(@RequestBody CollegeDo collegeDo) {
        List<CollegeDo> texts = collegeService.findList(collegeDo);
        return new ResultUtil<List<CollegeDo>>(true, StatusCode.OK, "条件查询成功", texts);
    }

    /**
     * 查询所有
     */
    @PostMapping("collegeFindAll")
    public ResultUtil<List<CollegeDo>> collegeFindAll() {
        List<CollegeDo> collegeDos = collegeService.findAll();
        return new ResultUtil<List<CollegeDo>>(true, StatusCode.OK, "查询成功", collegeDos);
    }

    /**
     * 新增
     */
    @PostMapping("collegeAdd")
    public ResultUtil collegeAdd(@RequestBody CollegeDo collegeDo) {
        CollegeDo item = new CollegeDo();
        collegeService.add(collegeDo);
        return new ResultUtil(true, StatusCode.OK, "新增成功！");
    }

    /**
     * 修改
     */
    @PostMapping("collegeUpdate")
    public ResultUtil collegeUpdate(@RequestBody CollegeDo collegeDo) {
        collegeService.updateById(collegeDo);
        return new ResultUtil(true, StatusCode.OK, "修改成功！");
    }

    /**
     * 根据ID删除
     */
    @PostMapping("collegeDelete")
    public ResultUtil collegeDelete(@RequestParam(value = "collegeId") String collegeId) {
        collegeService.delete(collegeId);
        return new ResultUtil(true, StatusCode.OK, "删除成功！");
    }

    /**
     * 条件+分页查询实现
     */
    @PostMapping("/collegeFindPage")
    public ResultUtil<PageResult<CollegeDo>> collegeFindPage(@RequestBody(required = false) CollegeDo collegeDo){
        PageResult<CollegeDo> pageResult = collegeService.findPage(collegeDo,collegeDo.getPage(),collegeDo.getSize());
        return new ResultUtil<PageResult<CollegeDo>>(true, StatusCode.OK, "分页查询成功",pageResult);
    }

}
