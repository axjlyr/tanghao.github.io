package com.wms.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.wms.entity.Zhaosheng;

public interface ZhaoshengService extends IService<Zhaosheng> {

    IPage queryfromSchool(IPage<Zhaosheng> page, Wrapper wrapper);

    void addZhaosheng(Zhaosheng sysZhaosheng);
    // 当数据库中有对应姓名记录时，更新
    void updateZhaosheng(Zhaosheng sysZhaosheng);
}
