package com.zyc.cloud.search.utils.jwt.mapper;

import com.zyc.cloud.search.utils.jwt.model.UserLoginVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
@Component
public interface UserLoginMapper{

    UserLoginVO login(UserLoginVO userLoginVO);

    UserLoginVO getUserInfo(@Param("account") String userId);

}
