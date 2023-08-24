package com.wms.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.wms.entity.Homepage;
import com.wms.entity.Zhaosheng;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface ZhaoshengMapper extends BaseMapper<Zhaosheng> {

    IPage queryfromSchool(IPage<Zhaosheng> page, @Param(Constants.WRAPPER) Wrapper wrapper);

    void addZhaosheng(Zhaosheng sysZhaosheng);
    // 当数据库中有对应姓名记录时，更新
    void updateZhaosheng(Zhaosheng sysZhaosheng);
}
