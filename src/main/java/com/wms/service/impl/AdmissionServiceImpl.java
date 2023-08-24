package com.wms.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wms.entity.Admission;
import com.wms.mapper.AdmissionMapper;
import com.wms.service.AdmissionService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class AdmissionServiceImpl extends ServiceImpl<AdmissionMapper, Admission> implements AdmissionService {

    @Resource
    private AdmissionMapper admissionMapper;
    @Override
    public IPage queryfromYear(IPage<Admission> page, Wrapper wrapper) {
        return admissionMapper.queryfromYear(page,wrapper);
    }

    @Override
    public void addAdmission(Admission sysAdmission) {
        admissionMapper.addAdmission(sysAdmission);
    }

    @Override
    public void updateAdmission(Admission sysAdmission) {
        admissionMapper.updateAdmission(sysAdmission);
    }
}
