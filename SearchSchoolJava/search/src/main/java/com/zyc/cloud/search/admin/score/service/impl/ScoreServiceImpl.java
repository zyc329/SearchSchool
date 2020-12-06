package com.zyc.cloud.search.admin.score.service.impl;

import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.github.pagehelper.PageHelper;
import com.zyc.cloud.search.admin.score.mapper.ScoreMapper;
import com.zyc.cloud.search.admin.score.model.ScoreDo;
import com.zyc.cloud.search.admin.score.service.ScoreService;
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
public class ScoreServiceImpl implements ScoreService {
    @Autowired
    private ScoreMapper scoreMapper;

    @Override
    public void add(ScoreDo scoreDo) {
        scoreDo.setScoreId(IdUtil.objectId());
        scoreMapper.insertSelective(scoreDo);
    }

    @Override
    public void updateById(ScoreDo scoreDo) {
        scoreMapper.updateByPrimaryKeySelective(scoreDo);
    }

    @Override
    public ScoreDo findById(String id) {
        return scoreMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<ScoreDo> findAll() {
        return scoreMapper.selectAll();
    }

    @Override
    public List<ScoreDo> findList(ScoreDo scoreDo) {
        Example example = createExample(scoreDo);
        return scoreMapper.selectByExample(example);
    }

    @Override
    public void delete(String id) {
        scoreMapper.deleteByPrimaryKey(id);
    }


    @Override
    public PageResult<ScoreDo> findPage(ScoreDo scoreDo, int page, int size) {
        PageHelper.startPage(page, size);
        Example example = createExample(scoreDo);
        List<ScoreDo> scoreDos = scoreMapper.selectByExample(example);
        return new PageResult<ScoreDo>(scoreDos);
    }

    public Example createExample(ScoreDo scoreDo) {
        Example example = new Example(ScoreDo.class);
        Example.Criteria criteria = example.createCriteria();
        if (ObjectUtil.isNotEmpty(scoreDo)) {
            if (scoreDo.getSchoolId() != null) {
                criteria.andEqualTo("schoolId", scoreDo.getSchoolId());
            }
            if (scoreDo.getProfessionalId() != null) {
                criteria.andEqualTo("professionalId", scoreDo.getProfessionalId());
            }
            if (scoreDo.getYear() != null) {
                criteria.andEqualTo("year", scoreDo.getYear());
            }
        }
        return example;
    }
}
