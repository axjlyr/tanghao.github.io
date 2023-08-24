package com.wms.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wms.entity.Zhaosheng;
import com.wms.mapper.ZhaoshengMapper;
import com.wms.service.ZhaoshengService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class ZhaoshengServiceImpl extends ServiceImpl<ZhaoshengMapper, Zhaosheng> implements ZhaoshengService {

    @Resource
    private ZhaoshengMapper zhaoshengMapper;


    @Override
    public IPage queryfromSchool(IPage<Zhaosheng> page, Wrapper wrapper) {
        return zhaoshengMapper.queryfromSchool(page,wrapper);
    }

    @Override
    public void addZhaosheng(Zhaosheng sysZhaosheng) {
        zhaoshengMapper.addZhaosheng(sysZhaosheng);
    }

    @Override
    public void updateZhaosheng(Zhaosheng sysZhaosheng) {
        zhaoshengMapper.updateZhaosheng(sysZhaosheng);
    }
}
