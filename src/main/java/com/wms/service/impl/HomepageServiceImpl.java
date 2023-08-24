package com.wms.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wms.entity.Admission;
import com.wms.entity.Homepage;
import com.wms.entity.SearchInfo;
import com.wms.mapper.HomepageMapper;
import com.wms.service.HomepageService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class HomepageServiceImpl extends ServiceImpl<HomepageMapper, Homepage> implements HomepageService {

    @Resource
    private HomepageMapper homepageMapper;
    @Override
    public IPage pageSelect(IPage<Homepage> page, Wrapper wrapper) {
        return homepageMapper.pageSelect(page,wrapper);
    }

    @Override
    public IPage pageMajor(IPage<Homepage> page, String majorBatch, String type, String major) {
        return homepageMapper.pageMajor(page,majorBatch,type,major);
    }

    /*@Override
    public IPage pageMajor(IPage<Homepage> page, String major) {
        return homepageMapper.pageMajor(page,major);
    }*/

    @Override
    public void addHomepage(Homepage sysHomepage) {
        homepageMapper.addHomepage(sysHomepage);
    }

    @Override
    public void updateHomepage(Homepage sysHomepage) {
        homepageMapper.updateHomepage(sysHomepage);
    }

    @Override
    public IPage pagePlace(IPage<Homepage> page, int up, int down, String types, String type) {
        return homepageMapper.pagePlace(page,down,up,types,type);
    }

    @Override
    public List<Homepage> searchBySchool(SearchInfo searchInfo) {
        int num = (searchInfo.getPageNum()-1)* searchInfo.getPageSize();
        return homepageMapper.searchBySchool(num, searchInfo.getPageSize(), searchInfo.getInput(), searchInfo.getIs985(), searchInfo.getIs211(),searchInfo.getMajorBatch(),searchInfo.getType(), searchInfo.getSearchFlag());
    }

    @Override
    public List<Homepage> searchByMajor(SearchInfo searchInfo) {
        int num = (searchInfo.getPageNum()-1)* searchInfo.getPageSize();
        return homepageMapper.searchByMajor(num, searchInfo.getPageSize(), searchInfo.getInput(), searchInfo.getIs985(), searchInfo.getIs211(), searchInfo.getSearchFlag());
    }
}
