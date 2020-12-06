package com.zyc.cloud.search.admin.score.service.impl;

import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.ObjectUtil;
import com.github.pagehelper.PageHelper;
import com.zyc.cloud.search.admin.score.mapper.ScoreMapper;
import com.zyc.cloud.search.admin.score.model.ScoreDo;
import com.zyc.cloud.search.admin.score.service.ScoreService;
import com.zyc.cloud.search.utils.PageResult;
import com.zyc.cloud.search.utils.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.util.StringUtil;

import java.text.SimpleDateFormat;
import java.util.*;

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
		}
		return example;
	}

	/**
	 * 多个专业一个学校的echarts
	 */
	public List<HashMap<String,Object>> scoreBycolleges(HashMap<String, Object> map) {
		Example example = createExample1(map);
		List<ScoreDo> doList = scoreMapper.selectByExample(example);
		List<HashMap<String,Object>> res = changeList(doList,Integer.valueOf(map.get("year").toString()),
                map.get("professionalIds").toString());
		return res;
	}

	public Example createExample1(HashMap<String, Object> map) {
		Example example = new Example(ScoreDo.class);
		Example.Criteria criteria = example.createCriteria();
		if (ObjectUtil.isNotEmpty(map)) {
			if (map.get("professionalIds") != null) {
//				String[] results = map.get("professionalIds").toString().split(",");
//				List arr = Arrays.asList(results);
//				criteria.andIn("professionalId", arr);
				criteria.andCondition(" professional_id in (" + map.get("professionalIds").toString() + ") ");
			}

			if (map.get("schoolId") != null) {
				criteria.andEqualTo("schoolId", map.get("schoolId").toString());
			}

			if (map.get("year") != null) {
				criteria.andCondition(" year BETWEEN date_format(now(),'%Y')-"+
						map.get("year").toString()+"+1 and  date_format(now(),'%Y')");
//				criteria.andBetween("year", "date_format(now(),'%Y')+1 -" + map.get("year").toString()
//						, "date_format(now(),'%Y')");
			}
		}
		return example;
	}

	/**
	 * 字段值转换
	 */
	public List<HashMap<String,Object>> changeList(List<ScoreDo> doList, int year, String professionalIds) {
        List<HashMap<String,Object>> itemList = new ArrayList<>();
		if (StringUtil.isNotEmpty(professionalIds)) {
			String[] results = professionalIds.split(",");
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
            Date date = new Date();
            for (int i = 0; i < year ; i++){
                HashMap<String,Object> item = new HashMap<String,Object>();
                String year1 = String.valueOf(Integer.decode(sdf.format(date))-i);
                item.put("year",year1);
                for (String result : results) {
                    item.put(result,0);
                }
                itemList.add(item);
            }

            for (ScoreDo od: doList){
                for (HashMap<String,Object> map : itemList) {
                    if (map.get("year").equals(od.getYear())){
                        map.put(od.getProfessionalId(),od.getScore());
                    }
                }
            }
		}
		return itemList;
	}
}
