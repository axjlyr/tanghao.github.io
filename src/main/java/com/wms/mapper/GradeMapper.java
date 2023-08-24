package com.wms.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wms.entity.Grade;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface GradeMapper extends BaseMapper<Grade> {

    void addGrade(Grade sysGrade);

    Grade queryfromMark(int mark,String type);

    Grade queryMax(String type);

    Grade queryMin(String type);
}
