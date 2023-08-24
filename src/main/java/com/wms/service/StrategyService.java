package com.wms.service;


import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.wms.entity.Strategy;

public interface StrategyService extends IService<Strategy> {
    IPage pageSelect(IPage<Strategy> page, Wrapper wrapper);

    void addStrategy(Strategy sysStrategy);
    // 当数据库中有对应姓名记录时，更新
    void updateStrategy(Strategy sysStrategy);
}
