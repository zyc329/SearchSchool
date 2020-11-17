package com.zyc.cloud.search.admin.school.service;

import com.zyc.cloud.search.admin.school.model.PicDo;
import com.zyc.cloud.search.admin.school.model.SchoolDo;
import com.zyc.cloud.search.admin.school.model.SchoolDo;
import com.zyc.cloud.search.admin.user.model.UserDo;
import com.zyc.cloud.search.utils.PageResult;

import java.util.List;

/**
 * @author ZengYc
 * @Date 2020/11/5 21:39
 */
public interface SchoolService {

	/**
	 * 新增
	 */
	void add(SchoolDo schoolDo);

	/**
	 * 新增图片
	 */
	void picAdd(List<PicDo> picDos);

	/**
	 * 条件查询
	 */
	List<SchoolDo> findList(SchoolDo schoolDo);

	/**
	 * 根据ID查询
	 * @return
	 */
	SchoolDo findById(String id);

	/**
	 * 根据id修改
	 */
	Integer updateById(SchoolDo schoolDo);

	/**
	 * 根据图片id查询
	 */
	List<PicDo> findPicsById(String pics);

	/**
	 * 根据id修改
	 */
	void addSchoolSearch(String schoolId);

	/**
	 * 根据ID删除
	 */
	void delete(String id);

	/**
	 * 分页+条件搜索
	 */
	PageResult<SchoolDo> findPage(SchoolDo schoolDo, int page, int size,String ranking,String seach);
}
