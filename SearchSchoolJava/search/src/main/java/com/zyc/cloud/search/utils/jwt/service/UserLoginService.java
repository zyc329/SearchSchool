package com.zyc.cloud.search.utils.jwt.service;

import com.zyc.cloud.search.utils.jwt.model.UserLoginModel;

public interface UserLoginService {
    UserLoginModel login(UserLoginModel userLoginModel);
}
