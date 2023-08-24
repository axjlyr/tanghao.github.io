package com.wms.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wms.entity.Strategy;
import com.wms.mapper.StrategyMapper;
import com.wms.service.StrategyService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class StrategyServiceImpl extends ServiceImpl<StrategyMapper, Strategy> implements StrategyService{

    @Resource
    private StrategyMapper strategyMapper;
    @Override
    public IPage pageSelect(IPage<Strategy> page, Wrapper wrapper) {
        return strategyMapper.pageSelect(page,wrapper);
    }

    @Override
    public void addStrategy(Strategy sysStrategy) {
        strategyMapper.addStrategy(sysStrategy);
    }

    @Override
    public void updateStrategy(Strategy sysStrategy) {
        strategyMapper.updateStrategy(sysStrategy);
    }
}
