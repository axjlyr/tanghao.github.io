package com.wms.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wms.entity.Grade;

public interface GradeService extends IService<Grade> {

    void addGrade(Grade sysGrade);

    Grade queryfromMark(int mark,String type);

    Grade queryMax(String type);

    Grade queryMin(String type);
}
