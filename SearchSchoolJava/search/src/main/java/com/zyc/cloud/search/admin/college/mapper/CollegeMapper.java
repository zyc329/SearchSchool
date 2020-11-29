package com.zyc.cloud.search.admin.college.mapper;

import com.zyc.cloud.search.admin.college.model.CollegeDo;
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
public interface CollegeMapper extends MyBaseMapper<CollegeDo> {


}
