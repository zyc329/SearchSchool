package com.zyc.cloud.search.admin.school.mapper;

import com.zyc.cloud.search.admin.school.model.SchoolDo;
import com.zyc.cloud.search.utils.base.MyBaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

/**
 * @author ZengYc
 * @Date 2020/11/5 21:35
 */
@Mapper
@Repository
@Component
public interface SchoolMapper extends MyBaseMapper<SchoolDo> {
}
