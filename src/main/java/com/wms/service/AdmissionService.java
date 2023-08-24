package com.wms.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.wms.entity.Admission;
import com.wms.entity.Zhaosheng;

public interface AdmissionService extends IService<Admission> {

    IPage queryfromYear(IPage<Admission> page, Wrapper wrapper);

    void addAdmission(Admission sysAdmission);
    // 当数据库中有对应姓名记录时，更新
    void updateAdmission(Admission sysAdmission);
}
