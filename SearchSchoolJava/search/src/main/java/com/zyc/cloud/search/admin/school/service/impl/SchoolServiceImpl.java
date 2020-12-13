package com.zyc.cloud.search.admin.school.service.impl;

import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.github.pagehelper.PageHelper;
import com.zyc.cloud.search.admin.school.mapper.PicMapper;
import com.zyc.cloud.search.admin.school.mapper.SchoolMapper;
import com.zyc.cloud.search.admin.school.model.PicDo;
import com.zyc.cloud.search.admin.school.model.SchoolDo;
import com.zyc.cloud.search.admin.school.model.SchoolItemVo;
import com.zyc.cloud.search.admin.school.service.SchoolService;
import com.zyc.cloud.search.admin.user.model.UserDo;
import com.zyc.cloud.search.utils.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * @author ZengYc
 * @Date 2020/11/5 21:39
 */
@Service
public class SchoolServiceImpl  implements SchoolService {

	@Autowired
	private SchoolMapper schoolMapper;

	@Autowired
	private PicMapper picMapper;

	@Override
	public void add(SchoolDo schoolDo) {
		schoolDo.setSchoolId(IdUtil.objectId());
		schoolMapper.insertSelective(schoolDo);
	}

	@Override
	public void picAdd(List<PicDo> picDos) {
		picMapper.insertList(picDos);
	}

	@Override
	public List<SchoolDo> findAll() {
		return schoolMapper.selectAll();
	}

	@Override
	public Integer updateById(SchoolDo schoolDo) {
		return schoolMapper.updateByPrimaryKey(schoolDo);
	}

	@Override
	public void delete(String id) {
		schoolMapper.deleteByPrimaryKey(id);
	}

	@Override
	public List<SchoolDo> findList(SchoolDo schoolDo) {
		Example example = createExample(schoolDo);
		return schoolMapper.selectByExample(example);
	}

	@Override
	public SchoolDo findById(String id) {
		return schoolMapper.selectByPrimaryKey(id);
	}
	@Override
	public void addSchoolSearch(String schoolId) {
		SchoolDo item = schoolMapper.selectByPrimaryKey(schoolId);
		Integer num = 1;
		String seach = item.getSchoolSeachSize();
	    if (ObjectUtil.isNotEmpty(seach)){
			num = Integer.valueOf(seach);
			num += 1;
		}
		item.setSchoolSeachSize(num.toString());
		schoolMapper.updateByPrimaryKey(item);
	}

	@Override
	public List<PicDo> findPicsById(String pics) {
		List<PicDo> picList = new ArrayList<>();
		if (ObjectUtil.isNotEmpty(pics)){
			Example example = selectPicByExample(pics);
			picList = picMapper.selectByExample(example);
		}
		return picList;
	};

	@Override
	public PageResult<SchoolDo> findPage(SchoolDo schoolDo, int page, int size, String ranking, String seach) {
		PageHelper.startPage(page, size);
		Example example = createExample(schoolDo);

		if (StrUtil.isNotEmpty(ranking) && StrUtil.isEmpty(seach)){
			example.setOrderByClause("school_ranking_size+0");
		}

		if (StrUtil.isNotEmpty(seach)  && StrUtil.isEmpty(ranking)){
			example.setOrderByClause("school_seach_size+0 desc");
		}

		List<SchoolDo> schoolDos = schoolMapper.selectByExample(example);
		return new PageResult<SchoolDo>(schoolDos);
	}

	public Example createExample(SchoolDo schoolDo) {
		Example example = new Example(SchoolDo.class);
		Example.Criteria criteria = example.createCriteria();
		if (ObjectUtil.isNotEmpty(schoolDo)) {
			if (StrUtil.isNotEmpty(schoolDo.getSchoolName())) {
				criteria.andLike("schoolName","%"+schoolDo.getSchoolName()+"%");
			}
			if (StrUtil.isNotEmpty(schoolDo.getSchoolType())) {
				criteria.andEqualTo("schoolType", schoolDo.getSchoolType());
			}
			if (null != schoolDo.getSchoolTime()){
				Date date = schoolDo.getSchoolTime();
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				criteria.andCondition("date_format('" + sdf.format(date) + "','%Y') = date_format(school_time,'%Y')");
			}
		}

		return example;
	}

	public Example selectPicByExample(String ids) {
		Example example = new Example(PicDo.class);
		Example.Criteria criteria = example.createCriteria();
		if (ObjectUtil.isNotEmpty(ids)) {
			criteria.setAndOr("pic_id in "+ids);
//			criteria.andCondition("pic_id in "+ids);
		}
		return example;
	}

	public List<SchoolItemVo> selectItem(HashMap<String,String> example, ArrayList<String> roleNameList) {
		List<SchoolItemVo> SchoolItemVos = schoolMapper.selectItem(example,roleNameList);
		return SchoolItemVos;
	}
}
