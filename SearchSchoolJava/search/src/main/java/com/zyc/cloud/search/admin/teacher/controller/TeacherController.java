package com.zyc.cloud.search.admin.teacher.controller;

import com.zyc.cloud.search.admin.teacher.model.TeacherDo;
import com.zyc.cloud.search.admin.teacher.service.TeacherService;
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
@RequestMapping(value = "/teacher")
@CrossOrigin
public class TeacherController {
    @Autowired
    private TeacherService teacherService;

    /**
     * 根据主键ID查询
     */
    @PostMapping("teacherFindId")
    public ResultUtil<TeacherDo> teacherFindId(@RequestBody String teacherId) {
        // 调用Service实现查询
        TeacherDo teacherDo = teacherService.findById(teacherId);
        return ResultUtil.success("查询成功", teacherDo);
    }

    /**
     * 条件查询
     */
    @PostMapping("teacherFindList")
    public ResultUtil<List<TeacherDo>> teacherFindList(@RequestBody TeacherDo teacherDo) {
        List<TeacherDo> texts = teacherService.findList(teacherDo);
        return new ResultUtil<List<TeacherDo>>(true, StatusCode.OK, "条件查询成功", texts);
    }

    /**
     * 查询所有
     */
    @PostMapping("teacherFindAll")
    public ResultUtil<List<TeacherDo>> teacherFindAll() {
        List<TeacherDo> teacherDos = teacherService.findAll();
        return new ResultUtil<List<TeacherDo>>(true, StatusCode.OK, "查询成功", teacherDos);
    }

    /**
     * 新增
     */
    @PostMapping("teacherAdd")
    public ResultUtil teacherAdd(@RequestBody TeacherDo teacherDo) {
        TeacherDo item = new TeacherDo();
        teacherService.add(teacherDo);
        return new ResultUtil(true, StatusCode.OK, "新增成功！");
    }

    /**
     * 修改
     */
    @PostMapping("teacherUpdate")
    public ResultUtil teacherUpdate(@RequestBody TeacherDo teacherDo) {
        teacherService.updateById(teacherDo);
        return new ResultUtil(true, StatusCode.OK, "修改成功！");
    }

    /**
     * 根据ID删除
     */
    @PostMapping("teacherDelete")
    public ResultUtil teacherDelete(@RequestParam(value = "teacherId") String teacherId) {
        teacherService.delete(teacherId);
        return new ResultUtil(true, StatusCode.OK, "删除成功！");
    }

    /**
     * 条件+分页查询实现
     */
    @PostMapping("/teacherFindPage")
    public ResultUtil<PageResult<TeacherDo>> teacherFindPage(@RequestBody(required = false) TeacherDo teacherDo,
                                                                  @RequestParam(required = false, defaultValue = "1") int page,
                                                                  @RequestParam(required = false, defaultValue = "10") int size){
        PageResult<TeacherDo> pageResult = teacherService.findPage(teacherDo,page,size);
        return new ResultUtil<PageResult<TeacherDo>>(true, StatusCode.OK, "分页查询成功",pageResult);
    }

}
