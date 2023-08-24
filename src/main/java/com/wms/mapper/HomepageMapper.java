package com.wms.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.wms.entity.Homepage;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface HomepageMapper extends BaseMapper<Homepage> {

    IPage pageSelect(IPage<Homepage> page, @Param(Constants.WRAPPER) Wrapper wrapper);

    IPage pageMajor(IPage<Homepage> page,String majorBatch,String type,String major);
    IPage pageMajor(IPage<Homepage> page,String major);
    void addHomepage(Homepage sysHomepage);
    // 当数据库中有对应姓名记录时，更新
    void updateHomepage(Homepage sysHomepage);

    IPage pagePlace(IPage<Homepage> page,int up,int down,String types,String type);

    List<Homepage> searchBySchool(int pageNum, int pageSize, String input, int nef, int too, String majorBatch,String type, String flag);

    List<Homepage> searchByMajor(int pageNum, int pageSize, String input, int nef, int too, String flag);


}
