package com.wms.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.wms.entity.Sort;

public interface SortService extends IService<Sort> {

    IPage pageSelect(IPage<Sort> page, Wrapper wrapper);
}
