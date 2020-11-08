package com.zyc.cloud.search.utils.jwt.mapper;

import com.zyc.cloud.search.utils.jwt.model.UserLoginModel;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
@Component
public interface UserLoginMapper{

    UserLoginModel login(UserLoginModel userLoginModel);

}
