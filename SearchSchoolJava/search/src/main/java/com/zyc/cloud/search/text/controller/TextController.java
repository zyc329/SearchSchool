package com.zyc.cloud.search.text.controller;

import com.github.pagehelper.PageInfo;
import com.zyc.cloud.search.admin.user.model.UserDo;
import com.zyc.cloud.search.text.model.Text;
import com.zyc.cloud.search.text.service.TextService;
import com.zyc.cloud.search.utils.PageResult;
import com.zyc.cloud.search.utils.ResultUtil;
import com.zyc.cloud.search.utils.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @Author ZengYc
 * @Date 2020/9/23 22:59
 */
@RestController
@RequestMapping(value = "/text")
/**
 * 跨域
 */
@CrossOrigin
public class TextController {

    @Autowired
    private TextService textService;

    /**
     * 条件+分页查询实现
     */
    @PostMapping("/findPage/{page}/{size}")
    public ResultUtil<PageResult<Text>> findPage(@RequestBody Text text,
                                               @PathVariable(value = "page")Integer page,
                                               @PathVariable(value = "size")Integer size){
        PageResult<Text> pageResult =textService.findPage(text,page, size);
        return new ResultUtil<PageResult<Text>>(true, StatusCode.OK, "条件+分页查询成功",pageResult);
    }

    /**
     * 分页查询实现
     */
    @GetMapping("/findPage/{page}/{size}")
    public ResultUtil<PageInfo<Text>> findPage(@PathVariable(value = "page")Integer page,
                                   @PathVariable(value = "size")Integer size){
        PageInfo<Text> pageInfo =textService.findPage(page, size);
        return new ResultUtil<PageInfo<Text>>(true, StatusCode.OK, "分页查询成功",pageInfo);
    }

    /**
     * 条件查询
     */
    @PostMapping("textFindList")
    public ResultUtil<List<Text>> findList(@RequestBody Text text) {
        List<Text> texts = textService.findList(text);
        return new ResultUtil<List<Text>>(true, StatusCode.OK, "条件查询成功", texts);
    }

    /**
     * 根绝ID删除实现
     */
    @DeleteMapping(value = "/{id}")
    public ResultUtil delete(@PathVariable(value = "id") String id) {
        textService.delete(id);
        return new ResultUtil(true, StatusCode.OK, "删除成功！");
    }

    @PostMapping("textDelete")
    public ResultUtil delete2(@RequestBody Text text) {
        textService.delete(text.getId());
        return new ResultUtil(true, StatusCode.OK, "删除成功！");
    }

    /**
     * 修改text用户
     */
    @PutMapping(value = "/{id}")
    public ResultUtil update(@PathVariable(value = "id") String id, @RequestBody Text text) {
        text.setId(id);
        textService.update(text);
        return new ResultUtil(true, StatusCode.OK, "修改成功！");
    }

    /**
     * 修改text用户2
     */
    @PostMapping("textUpdate")
    public ResultUtil update2(@RequestBody Text text) {
        textService.update(text);
        return new ResultUtil(true, StatusCode.OK, "修改成功！");
    }

    /**
     * 增加text用户
     */
    @PostMapping("textAdd")
    public ResultUtil add(@RequestBody Text text) {
        textService.add(text);
        return new ResultUtil(true, StatusCode.OK, "新增成功！");
    }

    /**
     * 根据主键ID查询
     */
    @GetMapping(value = "/{id}")
    public ResultUtil<Text> findById(@PathVariable(value = "id") Integer id) {
        // 调用Service实现查询
        Text text = textService.findById(id);
        return ResultUtil.success("查询成功", text);
    }

    /**
     * 查询所有
     */
    @GetMapping
    public ResultUtil<List<Text>> findAll() {
        List<Text> texts = textService.findAll();
        return new ResultUtil<List<Text>>(true, StatusCode.OK, "查询成功", texts);
    }

    /**
     * 查询所有
     */
    @GetMapping
    @RequestMapping("/text123")
    public ResultUtil<List<Text>> findAll1() {
        List<Text> texts = textService.findAll();
        return new ResultUtil<List<Text>>(true, StatusCode.OK, "查询成功", texts);
    }

    /**
     * 根据主键ID查询
     */
    @GetMapping(value = "/text234/{id}")
    public ResultUtil<Text> findById1(@PathVariable(value = "id") Integer id) {
        // 调用Service实现查询
        Text text = textService.findById(id);
        return new ResultUtil<Text>(true, StatusCode.OK, "查询成功", text);
    }

    @PostMapping("textText")
    public ResultUtil textText(@RequestBody UserDo userDo,Text text){
        System.out.println("进入测试！");
        return new ResultUtil(true, StatusCode.OK, "新增成功！");
    }
}
