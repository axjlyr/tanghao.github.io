package com.wms.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.wms.entity.Batch;
import com.wms.entity.Strategy;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface StrategyMapper extends BaseMapper<Strategy> {
    IPage pageSelect(IPage<Strategy> page, @Param(Constants.WRAPPER) Wrapper wrapper);

    void addStrategy(Strategy sysStrategy);
    // 当数据库中有对应姓名记录时，更新
    void updateStrategy(Strategy sysStrategy);
}
