package com.zyc.cloud.search.admin.user.controller;

import com.zyc.cloud.search.admin.user.model.UserDo;
import com.zyc.cloud.search.admin.user.service.UserService;
import com.zyc.cloud.search.text.model.Text;
import com.zyc.cloud.search.utils.PageResult;
import com.zyc.cloud.search.utils.ResultUtil;
import com.zyc.cloud.search.utils.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

/**
 * @author ZengYc
 * @Date 2020/11/5 21:07
 */
@RestController
@RequestMapping(value = "/user")
@CrossOrigin
public class UserController {
    @Autowired
    private UserService userService;

    /**
     * 根据主键ID查询
     */
    @PostMapping("userFindId")
    public ResultUtil<Text> userFindId(@RequestBody String userId) {
        // 调用Service实现查询
        UserDo userDo = userService.findById(userId);
        return ResultUtil.success("查询成功", userDo);
    }

    /**
     * 条件查询
     */
    @PostMapping("userFindList")
    public ResultUtil<List<UserDo>> userFindList(@RequestBody UserDo userDo) {
        List<UserDo> texts = userService.findList(userDo);
        return new ResultUtil<List<UserDo>>(true, StatusCode.OK, "条件查询成功", texts);
    }

    /**
     * 查询所有
     */
    @PostMapping("userFindAll")
    public ResultUtil<List<UserDo>> userFindAll() {
        List<UserDo> UserDos = userService.findAll();
        return new ResultUtil<List<UserDo>>(true, StatusCode.OK, "查询成功", UserDos);
    }

    /**
     * 新增
     */
    @PostMapping("userAdd")
    public ResultUtil userAdd(@RequestBody UserDo userDo) {
        UserDo item = new UserDo();
        item.setAccount(userDo.getAccount());
        if (userService.findList(item).size() > 0){
            return new ResultUtil(false, StatusCode.ERROR, "用户已存在！");
        }
        userService.add(userDo);
        return new ResultUtil(true, StatusCode.OK, "新增成功！");
    }

    /**
     * 修改
     */
    @PostMapping("userUpdate")
    public ResultUtil userUpdate(@RequestBody UserDo userDo) {
        userService.updateById(userDo);
        return new ResultUtil(true, StatusCode.OK, "修改成功！");
    }

    /**
     * 根据ID删除
     */
    @PostMapping("userDelete")
    public ResultUtil userDelete(@RequestBody UserDo userDo) {
        userService.delete(userDo.getUserId());
        return new ResultUtil(true, StatusCode.OK, "删除成功！");
    }

    /**
     * 状态修改
     */
    @PostMapping("userUpdateStatus")
    public ResultUtil userUpdateStatus(@RequestBody UserDo userDo) {
        Integer flag = userService.updateStatus(userDo);
        String message = "";
        if (flag != 1){
            message="修改状态失败！";
        }else{
            message="修改状态成功！";
        }
        return new ResultUtil(true, StatusCode.OK, message);
    }

    /**
     * 条件+分页查询实现
     */
    @PostMapping("/userFindPage")
    public ResultUtil<PageResult<UserDo>> userFindPage(@RequestBody(required = false) UserDo userDo,
                                                       @RequestParam(required = false, defaultValue = "1") int page,
                                                       @RequestParam(required = false, defaultValue = "10") int size){
        PageResult<UserDo> pageResult =userService.findPage(userDo,page,size);
        return new ResultUtil<PageResult<UserDo>>(true, StatusCode.OK, "分页查询成功",pageResult);
    }

    /**
     * 重置密码
     */
    @PostMapping("resetPW")
    public ResultUtil resetPW(@RequestParam(value = "userId") String userId) {
        Integer flag = userService.resetPW(userId);
        String message = "";
        if (flag != 1){
            message="重置密码失败！";
        }else{
            message="重置密码成功！";
        }
        return new ResultUtil(true, StatusCode.OK, message);
    }

    /**
     * 修改密码
     */
    @PostMapping("updatePW")
    public ResultUtil updatePW(@RequestParam(value = "userId") String userId,
                               @RequestParam(value = "newPw") String newPw) {
        Integer flag = userService.updatePW(userId,newPw);
        String message = "";
        if (flag != 1){
            message="修改密码失败！";
        }else{
            message="修改密码成功！";
        }
        return new ResultUtil(true, StatusCode.OK, message);
    }


}
