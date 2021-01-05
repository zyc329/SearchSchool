package com.zyc.cloud.search.admin.user.service.impl;

import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.github.pagehelper.PageHelper;
import com.zyc.cloud.search.admin.user.mapper.UserMapper;
import com.zyc.cloud.search.admin.user.model.UserDo;
import com.zyc.cloud.search.admin.user.service.UserService;
import com.zyc.cloud.search.utils.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * @author ZengYc
 * @Date 2020/11/5 21:39
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    private static final String PW = "123456";

    @Override
    public void add(UserDo userDo) {
        userDo.setUserId(IdUtil.objectId());
        userMapper.insertSelective(userDo);
    }

    @Override
    public void updateById(UserDo userDo) {
        userMapper.updateByPrimaryKeySelective(userDo);
    }

    @Override
    public UserDo findById(String id) {
        return userMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<UserDo> findAll() {
        return userMapper.selectAll();
    }

    @Override
    public List<UserDo> findList(UserDo userDo) {
        Example example = createExample(userDo);
        return userMapper.selectByExample(example);
    }

    @Override
    public void delete(String id) {
        userMapper.deleteByPrimaryKey(id);
    }

    /**
     * 修改用户状态
     *
     * @param userDo
     */
    @Override
    public Integer updateStatus(UserDo userDo) {
        UserDo findUserDo = findById(userDo.getUserId());
        if (ObjectUtil.isNotEmpty(findUserDo)) {
            if (StrUtil.isNotEmpty(findUserDo.getState().toString())) {
                if (findUserDo.getState() == 0) {
                    userDo.setState(1);
                } else {
                    userDo.setState(0);
                }
            } else {
                return 0;
            }
        }
        Integer flag = userMapper.updateByPrimaryKeySelective(userDo);
        return flag;
    }

    @Override
    public PageResult<UserDo> findPage(UserDo userDo, int page, int size) {
        PageHelper.startPage(page, size);
        Example example = createExample(userDo);
        List<UserDo> userDos = userMapper.selectByExample(example);
        return new PageResult<UserDo>(userDos);
    }

    /**
     * 重置密码
     */
    @Override
    public Integer resetPW(String userId) {

        String restPw = PW;
        return userMapper.resetPW(userId, restPw);
    }

    /**
     * 修改密码
     */
    @Override
    public Integer updatePW(String userId, String newPw) {
        return userMapper.resetPW(userId, newPw);
    }

    @Override
    public Integer forgetPW(UserDo userDo) {
        Example example = new Example(UserDo.class);
        Example.Criteria criteria = example.createCriteria();
        if (ObjectUtil.isNotEmpty(userDo)) {
            if (StrUtil.isNotEmpty(userDo.getAccount())) {
                criteria.andEqualTo("account", userDo.getAccount());
            }
        }
        List<UserDo> userList = userMapper.selectByExample(example);
        if (userList.size() == 0) {
            return 4;
        }
        UserDo user = userList.get(0);
        if (!userDo.getMiOne().equals(user.getMiOne())) {
            return 1;
        } else if (!userDo.getMiTwo().equals(user.getMiTwo())) {
            return 2;
        } else if (!userDo.getMiThree().equals(user.getMiThree())) {
            return 3;
        }
        resetPW(user.getUserId());
        return 5;
    }

    public Example createExample(UserDo userDo) {
        Example example = new Example(UserDo.class);
        Example.Criteria criteria = example.createCriteria();
        if (ObjectUtil.isNotEmpty(userDo)) {
            if (StrUtil.isNotEmpty(userDo.getAccount())) {
                criteria.andLike("account", "%" + userDo.getAccount() + "%");
            }
            if (StrUtil.isNotEmpty(userDo.getUserName())) {
                criteria.andLike("userName", "%" + userDo.getUserName() + "%");
            }
            if (userDo.getAge() != null) {
                criteria.andEqualTo("age", userDo.getAge());
            }
            if (userDo.getSex() != null) {
                criteria.andEqualTo("sex", userDo.getSex());
            }
            if (userDo.getRole() != null) {
                criteria.andEqualTo("role", userDo.getRole());
            }
        }
        return example;
    }

}
