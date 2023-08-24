package com.wms.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.wms.entity.Sell;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface SellMapper extends BaseMapper<Sell> {
    
    IPage pageSelect(IPage<Sell> page, @Param(Constants.WRAPPER) Wrapper wrapper);

    String pageSell(Sell sell);


}

