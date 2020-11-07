package com.zyc.cloud.search.admin.user.mapper;

import com.zyc.cloud.search.admin.user.model.UserDo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

/**
 * @author ZengYc
 * @Date 2020/11/5 21:35
 */
@org.apache.ibatis.annotations.Mapper
@Repository
public interface UserMapper extends Mapper<UserDo> {

	/**
	 * 重置密码
	 */
	void resetPW(@Param("userId") String userId , @Param("restPw") String restPw );

}
