package com.zyc.cloud.search.utils.jwt.service;

import com.zyc.cloud.search.utils.jwt.model.UserLoginVO;

public interface UserLoginService {
    UserLoginVO login(UserLoginVO userLoginVO);

    UserLoginVO gerUserInfo(String account);
}
