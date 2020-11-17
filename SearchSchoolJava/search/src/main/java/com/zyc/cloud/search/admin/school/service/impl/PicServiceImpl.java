package com.zyc.cloud.search.admin.school.service.impl;

import com.zyc.cloud.search.admin.school.mapper.PicMapper;
import com.zyc.cloud.search.admin.school.model.PicDo;
import com.zyc.cloud.search.admin.school.service.PicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author ZengYc
 * @Date 2020/11/18 0:25
 */
@Service
public class PicServiceImpl implements PicService {
    @Autowired
    private PicMapper picMapper;

    @Override
    public void add(PicDo picDo) {
        picMapper.insertSelective(picDo);
    }

    @Override
    public PicDo findById(String id) {
        return picMapper.selectByPrimaryKey(id);
    }
}
