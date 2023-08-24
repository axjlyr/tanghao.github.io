package com.wms.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wms.entity.Sell;
import com.wms.mapper.SellMapper;
import com.wms.service.SellService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class SellServiceImpl extends ServiceImpl<SellMapper, Sell> implements SellService {

    @Resource
    private SellMapper sellMapper;

    @Override
    public IPage pageSelect(IPage<Sell> page, Wrapper wrapper) {
        return sellMapper.pageSelect(page,wrapper);
    }

    @Override
    public String pageSell(Sell sell) {
        return sellMapper.pageSell(sell);
    }
}
