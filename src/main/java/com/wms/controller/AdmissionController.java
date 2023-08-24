package com.wms.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wms.common.AdmissionUploadExcelUtil;
import com.wms.common.QueryPageParam;
import com.wms.common.Result;
import com.wms.entity.Admission;
import com.wms.service.AdmissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;

@RestController
@RequestMapping("/admission")
public class AdmissionController {

    @Autowired
    private AdmissionService admissionService;

    @Autowired
    private AdmissionUploadExcelUtil admissionUploadExcelUtil;

    @PostMapping("/save")
    public Result save(@RequestBody Admission admission){
        return admissionService.save(admission)?Result.suc():Result.fail();
    }
    //更新
    @PostMapping("/update")
    public Result update(@RequestBody Admission admission){
        return admissionService.updateById(admission)?Result.suc():Result.fail();
    }
    //删除
    @GetMapping("/del")
    public Result del(@RequestParam String id){
        return admissionService.removeById(id)?Result.suc():Result.fail();
    }

    @PostMapping("/queryfromYear")
    public Result querayfromYear(@RequestBody QueryPageParam query){

        HashMap param = query.getParam();
        String school = (String)param.get("school");
        String majorBatch = (String)param.get("majorBatch");
        String type = (String)param.get("type");
        String year = (String)param.get("year");

        Page<Admission> page = new Page();
        page.setCurrent(query.getPageNum());
        page.setSize(query.getPageSize());

        LambdaQueryWrapper<Admission> lambdaQueryWrapper = new LambdaQueryWrapper();
//        if(StringUtils.isNotBlank(school) && !"null".equals(school)){
//            lambdaQueryWrapper.like(Admission::getSchool,school);
//        }else {
//            return Result.fail();
//        }
        if(StringUtils.isNotBlank(school)){
            lambdaQueryWrapper.like(Admission::getSchool,school);
        }else {
            return Result.fail();
        }

//        if(StringUtils.isNotBlank(majorBatch) && !"null".equals(majorBatch)){
//            lambdaQueryWrapper.like(Admission::getMajorBatch,majorBatch);
//        }else {
//            return Result.fail();
//        }
        if(StringUtils.isNotBlank(majorBatch)){
            lambdaQueryWrapper.like(Admission::getMajorBatch,majorBatch);
        }else {
            return Result.fail();
        }

//        if(StringUtils.isNotBlank(type) && !"null".equals(type)){
//            lambdaQueryWrapper.like(Admission::getType,type);
//        }else {
//            return Result.fail();
//        }
        if(StringUtils.isNotBlank(type)){
            lambdaQueryWrapper.like(Admission::getType,type);
        }else {
            return Result.fail();
        }

//        if(StringUtils.isNotBlank(year) && !"null".equals(year)){
//            lambdaQueryWrapper.like(Admission::getYear,year);
//        }
        if(StringUtils.isNotBlank(year)){
            lambdaQueryWrapper.like(Admission::getYear,year);
        }

        IPage result =admissionService.queryfromYear(page,lambdaQueryWrapper);

        return Result.suc(result.getRecords(),result.getTotal());
    }

    @PostMapping("/upload")
    public Boolean upload(@RequestParam("file") MultipartFile file){

        boolean bool = false;
        String fileName = file.getOriginalFilename();
        try {
            bool = admissionUploadExcelUtil.batchImport(fileName, file);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return  bool;
    }
}
