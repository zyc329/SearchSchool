package com.zyc.cloud.search.admin.teacher.service.impl;

import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.github.pagehelper.PageHelper;
import com.zyc.cloud.search.admin.teacher.mapper.TeacherMapper;
import com.zyc.cloud.search.admin.teacher.model.TeacherDo;
import com.zyc.cloud.search.admin.teacher.service.TeacherService;
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
public class TeacherServiceImpl implements TeacherService {
    @Autowired
    private TeacherMapper teacherMapper;

    @Override
    public void add(TeacherDo teacherDo) {
        teacherDo.setTeacherId(IdUtil.objectId());
        teacherMapper.insertSelective(teacherDo);
    }

    @Override
    public void updateById(TeacherDo teacherDo) {
        teacherMapper.updateByPrimaryKeySelective(teacherDo);
    }

    @Override
    public TeacherDo findById(String id) {
        return teacherMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<TeacherDo> findAll() {
        return teacherMapper.selectAll();
    }

    @Override
    public List<TeacherDo> findList(TeacherDo teacherDo) {
        Example example = createExample(teacherDo);
        return teacherMapper.selectByExample(example);
    }

    @Override
    public void delete(String id) {
        teacherMapper.deleteByPrimaryKey(id);
    }


    @Override
    public PageResult<TeacherDo> findPage(TeacherDo teacherDo, int page, int size) {
        PageHelper.startPage(page, size);
        Example example = createExample(teacherDo);
        List<TeacherDo> teacherDos = teacherMapper.selectByExample(example);
        return new PageResult<TeacherDo>(teacherDos);
    }

    public Example createExample(TeacherDo teacherDo) {
        Example example = new Example(TeacherDo.class);
        Example.Criteria criteria = example.createCriteria();
        if (ObjectUtil.isNotEmpty(teacherDo)) {
            if (StrUtil.isNotEmpty(teacherDo.getTeacherName())) {
                criteria.andLike("teacherName", "%" + teacherDo.getTeacherName() + "%");
            }
            if (StrUtil.isNotEmpty(teacherDo.getTitle())) {
                criteria.andLike("title", "%" + teacherDo.getTitle() + "%");
            }
            if (teacherDo.getSchoolId() != null) {
                criteria.andEqualTo("schoolId", teacherDo.getSchoolId());
            }
            if (teacherDo.getCollegeId() != null) {
                criteria.andEqualTo("collegeId", teacherDo.getCollegeId());
            }
            if (teacherDo.getProfessionalId() != null) {
                criteria.andEqualTo("professionalId", teacherDo.getProfessionalId());
            }
            if (teacherDo.getTeachingAge() != null) {
                criteria.andEqualTo("teachingAge", teacherDo.getTeachingAge());
            }
        }
        return example;
    }

}
