package com.zyc.cloud.search.utils.jwt.service.impl;

import com.zyc.cloud.search.utils.jwt.mapper.UserLoginMapper;
import com.zyc.cloud.search.utils.jwt.model.UserLoginModel;
import com.zyc.cloud.search.utils.jwt.service.UserLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserLoginServiceImpl implements UserLoginService {
    @Autowired
    private UserLoginMapper userLoginMapper;

    @Override
    public UserLoginModel login(UserLoginModel userLoginModel) {
        return userLoginMapper.login(userLoginModel);
    }
}
