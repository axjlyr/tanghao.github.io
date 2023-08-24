package com.wms.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wms.common.HomepageUploadExcelUtil;
import com.wms.common.QueryPageParam;
import com.wms.common.Result;
import com.wms.entity.Homepage;
import com.wms.entity.Homepage;
import com.wms.entity.SearchInfo;
import com.wms.service.HomepageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/homepage")
public class HomepageController {

    @Autowired
    private HomepageService homepageService;
    @Autowired
    private HomepageUploadExcelUtil homepageUploadExcelUtil;

   @PostMapping("/search_school")
   public Result searchBySchool(@RequestBody SearchInfo searchInfo){
       List iPage = homepageService.searchBySchool(searchInfo);
       return Result.suc((Object) iPage, (long) iPage.size());
   }

    @PostMapping("/save")
    public Result save(@RequestBody Homepage homepage){
        return homepageService.save(homepage)? Result.suc():Result.fail();
    }
    //更新
    @PostMapping("/update")
    public Result update(@RequestBody Homepage homepage){
        return homepageService.updateById(homepage)?Result.suc():Result.fail();
    }
    //删除
    @GetMapping("/del")
    public Result del(@RequestParam String id){
        return homepageService.removeById(id)?Result.suc():Result.fail();
    }

    @PostMapping("/pageSelect")
    public Result pageSelect(@RequestBody QueryPageParam query){

        HashMap param = query.getParam();
        String majorBatch = (String)param.get("majorBatch");
        String type = (String)param.get("type");

        Page<Homepage> page = new Page();
        page.setCurrent(query.getPageNum());
        page.setSize(query.getPageSize());

        LambdaQueryWrapper<Homepage> lambdaQueryWrapper = new LambdaQueryWrapper();

        if(StringUtils.isNotBlank(majorBatch) && !"null".equals(majorBatch)){
            lambdaQueryWrapper.like(Homepage::getMajorBatch,majorBatch);
        }else {
            return Result.fail();
        }

        if(StringUtils.isNotBlank(type) && !"null".equals(type)){
            lambdaQueryWrapper.like(Homepage::getType,type);
        }else {
            return Result.fail();
        }

        IPage result =homepageService.pageSelect(page,lambdaQueryWrapper);

        return Result.suc(result.getRecords(),result.getTotal());
    }

    @PostMapping("/pageCity")
    public Result pageCity(@RequestBody QueryPageParam query){

        HashMap param = query.getParam();
        String majorBatch = (String)param.get("majorBatch");
        String type = (String)param.get("type");
        String city = (String)param.get("city");

        Page<Homepage> page = new Page();
        page.setCurrent(query.getPageNum());
        page.setSize(query.getPageSize());

        LambdaQueryWrapper<Homepage> lambdaQueryWrapper = new LambdaQueryWrapper();

        if(StringUtils.isNotBlank(majorBatch) && !"null".equals(majorBatch)){
            lambdaQueryWrapper.like(Homepage::getMajorBatch,majorBatch);
        }else {
            return Result.fail();
        }

        if(StringUtils.isNotBlank(type) && !"null".equals(type)){
            lambdaQueryWrapper.like(Homepage::getType,type);
        }else {
            return Result.fail();
        }

        if(StringUtils.isNotBlank(city) && !"null".equals(city)){
            lambdaQueryWrapper.like(Homepage::getCity,city);
        }

        IPage result =homepageService.pageSelect(page,lambdaQueryWrapper);

        return Result.suc(result.getRecords(),result.getTotal());
    }

    @PostMapping("/pageSchool")
    public Result pageSchool(@RequestBody QueryPageParam query){

        HashMap param = query.getParam();
        String majorBatch = (String)param.get("majorBatch");
        String type = (String)param.get("type");
        String school = (String)param.get("school");
        String nef = (String)param.get("nef");
        String too = (String)param.get("too");

        Page<Homepage> page = new Page();
        page.setCurrent(query.getPageNum());
        page.setSize(query.getPageSize());

        LambdaQueryWrapper<Homepage> lambdaQueryWrapper = new LambdaQueryWrapper();

        if(StringUtils.isNotBlank(majorBatch) && !"null".equals(majorBatch)){
            lambdaQueryWrapper.like(Homepage::getMajorBatch,majorBatch);
        }else {
            return Result.fail();
        }

        if(StringUtils.isNotBlank(type) && !"null".equals(type)){
            lambdaQueryWrapper.like(Homepage::getType,type);
        }else {
            return Result.fail();
        }

        if(StringUtils.isNotBlank(school) && !"null".equals(school)){
            lambdaQueryWrapper.like(Homepage::getSchool,school);
        }

        if(StringUtils.isNotBlank(nef) && !"null".equals(nef)){
            lambdaQueryWrapper.like(Homepage::getNef,nef);
        }

        if(StringUtils.isNotBlank(too) && !"null".equals(too)){
            lambdaQueryWrapper.like(Homepage::getToo,too);
        }


        IPage result =homepageService.pageSelect(page,lambdaQueryWrapper);

        return Result.suc(result.getRecords(),result.getTotal());
    }

    @PostMapping("/pageMajor")
    public Result pageMajor(@RequestBody QueryPageParam query){

        HashMap param = query.getParam();
        String majorBatch = (String)param.get("majorBatch");
        String type = (String)param.get("type");
        String major = (String)param.get("major");

        Page<Homepage> page = new Page();
        page.setCurrent(query.getPageNum());
        page.setSize(query.getPageSize());

        IPage result =homepageService.pageMajor(page,majorBatch,type,major);
//        IPage result =homepageService.pageMajor(page,major);
        return Result.suc(result.getRecords(),result.getTotal());
    }

    @PostMapping("/upload")
    public Boolean upload(@RequestParam("file") MultipartFile file){

        boolean bool = false;
        String fileName = file.getOriginalFilename();
        try {
            bool = homepageUploadExcelUtil.batchImport(fileName, file);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return  bool;
    }


}
