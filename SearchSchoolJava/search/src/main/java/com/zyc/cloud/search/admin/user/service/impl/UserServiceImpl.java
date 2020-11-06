package com.zyc.cloud.search.admin.user.service.impl;

import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.github.pagehelper.PageHelper;
import com.zyc.cloud.search.admin.user.mapper.UserMapper;
import com.zyc.cloud.search.admin.user.model.UserDo;
import com.zyc.cloud.search.admin.user.service.UserService;
import com.zyc.cloud.search.text.model.Text;
import com.zyc.cloud.search.utils.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
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

    @Override
    public void add(UserDo userDo) {
        userDo.setUserId(IdUtil.objectId());
        if (StrUtil.isEmpty(userDo.getState().toString())) {
            userDo.setState(0);
        }
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
    public PageResult<UserDo> findPage(UserDo userDo) {
        Integer page;
        Integer size;
        page = userDo.getPage() != null ? userDo.getPage() : 1;
        size = userDo.getSize() != null ? userDo.getSize() : 10;
        PageHelper.startPage(page, size);
        Example example = createExample(userDo);
        List<UserDo> userDos = userMapper.selectByExample(example);
        return new PageResult<UserDo>(userDos);
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
        }
        return example;
    }

}
