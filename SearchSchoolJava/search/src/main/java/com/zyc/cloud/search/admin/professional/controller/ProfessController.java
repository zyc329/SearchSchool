package com.zyc.cloud.search.admin.professional.controller;

import com.zyc.cloud.search.admin.professional.model.ProfessDo;
import com.zyc.cloud.search.admin.professional.service.ProfessService;
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
@RequestMapping(value = "/professional")
@CrossOrigin
public class ProfessController {
    @Autowired
    private ProfessService professService;

    /**
     * 根据主键ID查询
     */
    @PostMapping("professionalFindId")
    public ResultUtil<ProfessDo> professionalFindId(@RequestBody String professionalId) {
        // 调用Service实现查询
        ProfessDo professDo = professService.findById(professionalId);
        return ResultUtil.success("查询成功", professDo);
    }

    /**
     * 条件查询
     */
    @PostMapping("professionalFindList")
    public ResultUtil<List<ProfessDo>> professionalFindList(@RequestBody ProfessDo professDo) {
        List<ProfessDo> texts = professService.findList(professDo);
        return new ResultUtil<List<ProfessDo>>(true, StatusCode.OK, "条件查询成功", texts);
    }

    /**
     * 查询所有
     */
    @PostMapping("professionalFindAll")
    public ResultUtil<List<ProfessDo>> professionalFindAll() {
        List<ProfessDo> professDos = professService.findAll();
        return new ResultUtil<List<ProfessDo>>(true, StatusCode.OK, "查询成功", professDos);
    }

    /**
     * 新增
     */
    @PostMapping("professionalAdd")
    public ResultUtil professionalAdd(@RequestBody ProfessDo professDo) {
        ProfessDo item = new ProfessDo();
        professService.add(professDo);
        return new ResultUtil(true, StatusCode.OK, "新增成功！");
    }

    /**
     * 修改
     */
    @PostMapping("professionalUpdate")
    public ResultUtil professionalUpdate(@RequestBody ProfessDo professDo) {
        professService.updateById(professDo);
        return new ResultUtil(true, StatusCode.OK, "修改成功！");
    }

    /**
     * 根据ID删除
     */
    @PostMapping("professionalDelete")
    public ResultUtil professionalDelete(@RequestParam(value = "professionalId") String professionalId) {
        professService.delete(professionalId);
        return new ResultUtil(true, StatusCode.OK, "删除成功！");
    }

    /**
     * 条件+分页查询实现
     */
    @PostMapping("/professionalFindPage")
    public ResultUtil<PageResult<ProfessDo>> professionalFindPage(@RequestBody(required = false) ProfessDo professDo){
        PageResult<ProfessDo> pageResult = professService.findPage(professDo,professDo.getPage(),professDo.getSize());
        return new ResultUtil<PageResult<ProfessDo>>(true, StatusCode.OK, "分页查询成功",pageResult);
    }

}
