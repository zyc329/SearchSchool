package com.zyc.cloud.search.utils.jwt.service.impl;

import com.zyc.cloud.search.utils.jwt.mapper.UserLoginMapper;
import com.zyc.cloud.search.utils.jwt.model.UserLoginVO;
import com.zyc.cloud.search.utils.jwt.service.UserLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserLoginServiceImpl implements UserLoginService {
    @Autowired
    private UserLoginMapper userLoginMapper;

    @Override
    public UserLoginVO login(UserLoginVO userLoginVO) {
        return userLoginMapper.login(userLoginVO);
    }

    @Override
    public UserLoginVO gerUserInfo(String account) {
        return userLoginMapper.getUserInfo(account);
    }
}
