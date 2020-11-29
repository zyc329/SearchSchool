package com.zyc.cloud.search.admin.professional.service.impl;

import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.github.pagehelper.PageHelper;
import com.zyc.cloud.search.admin.professional.mapper.ProfessMapper;
import com.zyc.cloud.search.admin.professional.model.ProfessDo;
import com.zyc.cloud.search.admin.professional.service.ProfessService;
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
public class ProfessServiceImpl implements ProfessService {
    @Autowired
    private ProfessMapper professMapper;

    @Override
    public void add(ProfessDo professDo) {
        professDo.setProfessionalId(IdUtil.objectId());
        professMapper.insertSelective(professDo);
    }

    @Override
    public void updateById(ProfessDo professDo) {
        professMapper.updateByPrimaryKeySelective(professDo);
    }

    @Override
    public ProfessDo findById(String id) {
        return professMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<ProfessDo> findAll() {
        return professMapper.selectAll();
    }

    @Override
    public List<ProfessDo> findList(ProfessDo professDo) {
        Example example = createExample(professDo);
        return professMapper.selectByExample(example);
    }

    @Override
    public void delete(String id) {
        professMapper.deleteByPrimaryKey(id);
    }


    @Override
    public PageResult<ProfessDo> findPage(ProfessDo professDo, int page, int size) {
        PageHelper.startPage(page, size);
        Example example = createExample(professDo);
        List<ProfessDo> professDos = professMapper.selectByExample(example);
        return new PageResult<ProfessDo>(professDos);
    }

    public Example createExample(ProfessDo professDo) {
        Example example = new Example(ProfessDo.class);
        Example.Criteria criteria = example.createCriteria();
        if (ObjectUtil.isNotEmpty(professDo)) {
            if (StrUtil.isNotEmpty(professDo.getProfessionalName())) {
                criteria.andLike("professionalName", "%" + professDo.getProfessionalName() + "%");
            }
            if (professDo.getProfessionalId() != null) {
                criteria.andEqualTo("professionalId", professDo.getProfessionalId());
            }
            if (professDo.getCollegeId() != null) {
                criteria.andEqualTo("collegeId", professDo.getCollegeId());
            }
        }
        return example;
    }

}
