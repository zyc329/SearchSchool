package com.zyc.cloud.search.admin.user.mapper;

import com.zyc.cloud.search.admin.user.model.UserDo;
import com.zyc.cloud.search.utils.base.MyBaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

/**
 * @author ZengYc
 * @Date 2020/11/5 21:35
 */
@Mapper
@Repository
@Component
public interface UserMapper extends MyBaseMapper<UserDo> {

    /**
     * 重置密码
     */
    void resetPW(@Param("userId") String userId, @Param("restPw") String restPw);

}
