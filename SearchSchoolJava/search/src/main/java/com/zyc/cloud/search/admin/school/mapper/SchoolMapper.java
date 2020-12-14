package com.zyc.cloud.search.admin.school.mapper;

import com.zyc.cloud.search.admin.school.model.SchoolDo;
import com.zyc.cloud.search.admin.school.model.SchoolItemVo;
import com.zyc.cloud.search.utils.base.MyBaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author ZengYc
 * @Date 2020/11/5 21:35
 */
@Mapper
@Repository
@Component
public interface SchoolMapper extends MyBaseMapper<SchoolDo> {

	/**
	 * 查询
	 */
	List<SchoolItemVo> selectItem(@Param("example") HashMap<String,String> example,
								  @Param("roleNameList") ArrayList<String> roleNameList);

	void addCount(@Param("id") String schoolId);
}
