package com.wms.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wms.common.QueryPageParam;
import com.wms.common.Result;
import com.wms.common.ZhaoshengUplaodExcelUtil;
import com.wms.entity.Zhaosheng;
import com.wms.service.ZhaoshengService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;

@RestController
@RequestMapping("/zhaosheng")
public class ZhaoshengController {
    
    @Autowired
    private ZhaoshengService zhaoshengService;
    @Autowired
    private ZhaoshengUplaodExcelUtil  zhaoshengUplaodExcelUtil;

    @PostMapping("/save")
    public Result save(@RequestBody Zhaosheng zhaosheng){
        return zhaoshengService.save(zhaosheng)?Result.suc():Result.fail();
    }
    //更新
    @PostMapping("/update")
    public Result update(@RequestBody Zhaosheng zhaosheng){
        return zhaoshengService.updateById(zhaosheng)?Result.suc():Result.fail();
    }
    //删除
    @GetMapping("/del")
    public Result del(@RequestParam String id){
        return zhaoshengService.removeById(id)?Result.suc():Result.fail();
    }

    @PostMapping("/queryfromSchool")
    public Result querayfromSchool(@RequestBody QueryPageParam query){

        HashMap param = query.getParam();
        String school = (String)param.get("school");
        String majorBatch = (String)param.get("majorBatch");
        String type = (String)param.get("type");

        Page<Zhaosheng> page = new Page();
        page.setCurrent(query.getPageNum());
        page.setSize(query.getPageSize());

        LambdaQueryWrapper<Zhaosheng> lambdaQueryWrapper = new LambdaQueryWrapper();
        if(StringUtils.isNotBlank(school) && !"null".equals(school)){
            lambdaQueryWrapper.like(Zhaosheng::getSchool,school);
        }else {
            return Result.fail();
        }

        if(StringUtils.isNotBlank(majorBatch) && !"null".equals(majorBatch)){
            lambdaQueryWrapper.like(Zhaosheng::getMajorBatch,majorBatch);
        }else {
            return Result.fail();
        }

        if(StringUtils.isNotBlank(type) && !"null".equals(type)){
            lambdaQueryWrapper.like(Zhaosheng::getType,type);
        }else {
            return Result.fail();
        }

        IPage result =zhaoshengService.queryfromSchool(page,lambdaQueryWrapper);

        return Result.suc(result.getRecords(),result.getTotal());
    }

    @PostMapping("/upload")
    public Boolean upload(@RequestParam("file") MultipartFile file){

        boolean bool = false;
        String fileName = file.getOriginalFilename();
        try {
            bool = zhaoshengUplaodExcelUtil.batchImport(fileName, file);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return  bool;
    }
}
