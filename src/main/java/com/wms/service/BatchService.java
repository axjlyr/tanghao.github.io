package com.wms.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.wms.entity.Batch;

public interface BatchService extends IService<Batch>{

    IPage pageSelect(IPage<Batch> page, Wrapper wrapper);

    void addBatch(Batch sysBatch);
    // 当数据库中有对应姓名记录时，更新
    void updateBatch(Batch sysBatch);

    Boolean deleteFromNo(String no);
}
