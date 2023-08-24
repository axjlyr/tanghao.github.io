package com.wms.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wms.entity.Sort;
import com.wms.mapper.SortMapper;
import com.wms.service.SortService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class SortServiceImpl extends ServiceImpl<SortMapper, Sort> implements SortService {

    @Resource
    private SortMapper sortMapper;

    @Override
    public IPage pageSelect(IPage<Sort> page, Wrapper wrapper) {
        return sortMapper.pageSelect(page,wrapper);
    }
}
