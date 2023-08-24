package com.wms.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wms.entity.Grade;
import com.wms.mapper.GradeMapper;
import com.wms.service.GradeService;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.time.Year;

@Service
public class GradeServiceImpl extends ServiceImpl<GradeMapper, Grade> implements GradeService {

    @Resource
    private GradeMapper gradeMapper;

    @Override
    public void addGrade(Grade sysGrade) {
        gradeMapper.addGrade(sysGrade);
    }

    @Override
    public Grade queryfromMark(int mark, String type) {
        return gradeMapper.queryfromMark(mark,type);
    }

    @Override
    public Grade queryMax(String type) {
        return gradeMapper.queryMax(type);
    }

    @Override
    public Grade queryMin(String type) {
        return gradeMapper.queryMin(type);
    }
}
