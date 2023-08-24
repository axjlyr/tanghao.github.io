package com.wms.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.wms.entity.Homepage;
import com.wms.entity.SearchInfo;

import java.util.List;

public interface HomepageService extends IService<Homepage> {

    IPage pageSelect(IPage<Homepage> page, Wrapper wrapper);

    IPage pageMajor(IPage<Homepage> page,String majorBatch,String type,String major);

    //IPage pageMajor(IPage<Homepage> page,String major);
    void addHomepage(Homepage sysHomepage);
    // 当数据库中有对应姓名记录时，更新
    void updateHomepage(Homepage sysHomepage);

    IPage pagePlace(IPage<Homepage> page,int up,int down,String types,String type);

    List searchBySchool(SearchInfo searchInfo);

    List searchByMajor(SearchInfo searchInfo);
}
