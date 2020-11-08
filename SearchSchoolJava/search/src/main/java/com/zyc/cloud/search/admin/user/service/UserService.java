package com.zyc.cloud.search.admin.user.service;

import com.zyc.cloud.search.admin.user.model.UserDo;
import com.zyc.cloud.search.text.model.Text;
import com.zyc.cloud.search.utils.PageResult;

import java.util.List;

/**
 * @author ZengYc
 * @Date 2020/11/5 21:39
 */
public interface UserService {
    /**
     * 新增
     */
    void add(UserDo userDo);

    /**
     * 根据id修改
     */
    void updateById(UserDo userDo);

    /**
     * 根据ID查询
     * @return
     */
    UserDo findById(String id);

    /**
     * 查询所有
     */
    List<UserDo> findAll();

    /**
     * 条件查询
     */
    List<UserDo> findList(UserDo userDo);

    /**
     * 根据ID删除
     */
    void delete(String id);

    /**
     * 修改账号状态
     */
    Integer updateStatus(UserDo userDo);

    /**
     * 分页+条件搜索
     */
    PageResult<UserDo> findPage(UserDo userDo,int page,int size);

    /**
     * 密码重置
     */
    Integer resetPW(String userId);

    /**
     * 密码修改
     */
    Integer updatePW(String userId,String newPw);
}
