package com.wms.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wms.entity.Batch;
import com.wms.mapper.BatchMapper;
import com.wms.service.BatchService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class BatchServiceImpl extends ServiceImpl<BatchMapper,Batch> implements BatchService {

    @Resource
    private BatchMapper batchMapper;

    @Override
    public IPage pageSelect(IPage<Batch> page, Wrapper wrapper) {
        return batchMapper.pageSelect(page,wrapper);
    }

    @Override
    public void addBatch(Batch sysBatch) {
        batchMapper.addBatch(sysBatch);
    }

    @Override
    public void updateBatch(Batch sysBatch) {
        batchMapper.updateBatch(sysBatch);
    }

    @Override
    public Boolean deleteFromNo(String no) {
        return batchMapper.deleteFromNo(no);
    }
}
