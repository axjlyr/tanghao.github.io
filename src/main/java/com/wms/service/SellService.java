package com.wms.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.wms.entity.Sell;

public interface SellService extends IService<Sell> {

    IPage pageSelect(IPage<Sell> page, Wrapper wrapper);

    String pageSell(Sell sell);
}

