package com.zyc.cloud.search.admin.college.service.impl;

import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.github.pagehelper.PageHelper;
import com.zyc.cloud.search.admin.college.mapper.CollegeMapper;
import com.zyc.cloud.search.admin.college.model.CollegeDo;
import com.zyc.cloud.search.admin.college.service.CollegeService;
import com.zyc.cloud.search.utils.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * @author ZengYc
 * @Date 2020/11/5 21:39
 */
@Service
public class CollegeServiceImpl implements CollegeService {
    @Autowired
    private CollegeMapper collegeMapper;

    @Override
    public void add(CollegeDo collegeDo) {
        collegeDo.setCollegeId(IdUtil.objectId());
        collegeMapper.insertSelective(collegeDo);
    }

    @Override
    public void updateById(CollegeDo collegeDo) {
        collegeMapper.updateByPrimaryKeySelective(collegeDo);
    }

    @Override
    public CollegeDo findById(String id) {
        return collegeMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<CollegeDo> findAll() {
        return collegeMapper.selectAll();
    }

    @Override
    public List<CollegeDo> findList(CollegeDo collegeDo) {
        Example example = createExample(collegeDo);
        return collegeMapper.selectByExample(example);
    }

    @Override
    public void delete(String id) {
        collegeMapper.deleteByPrimaryKey(id);
    }


    @Override
    public PageResult<CollegeDo> findPage(CollegeDo collegeDo, int page, int size) {
        PageHelper.startPage(page, size);
        Example example = createExample(collegeDo);
        List<CollegeDo> collegeDos = collegeMapper.selectByExample(example);
        return new PageResult<CollegeDo>(collegeDos);
    }

    public Example createExample(CollegeDo collegeDo) {
        Example example = new Example(CollegeDo.class);
        Example.Criteria criteria = example.createCriteria();
        if (ObjectUtil.isNotEmpty(collegeDo)) {
            if (StrUtil.isNotEmpty(collegeDo.getCollegeName())) {
                criteria.andLike("collegeName", "%" + collegeDo.getCollegeName() + "%");
            }
            if (StrUtil.isNotEmpty(collegeDo.getCollegeHead())) {
                criteria.andLike("collegeHead", "%" + collegeDo.getCollegeHead() + "%");
            }
            if (collegeDo.getSchoolId() != null) {
                criteria.andEqualTo("schoolId", collegeDo.getSchoolId());
            }
        }
        return example;
    }
}
