package com.wms.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wms.common.QueryPageParam;
import com.wms.common.RandKami;
import com.wms.common.Result;
import com.wms.common.StrategyUploadExcelUtil;
import com.wms.entity.Strategy;
import com.wms.service.StrategyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/strategy")
public class StrategyController {
    
    @Autowired
    private StrategyService strategyService;

    @Autowired
    private StrategyUploadExcelUtil strategyUploadExcelUtil;

    @PostMapping("/save")
    public Result save(@RequestBody Strategy strategy){
        return strategyService.save(strategy)?Result.suc():Result.fail();
    }
    //更新
    @PostMapping("/update")
    public Result update(@RequestBody Strategy strategy){
        return strategyService.updateById(strategy)?Result.suc():Result.fail();
    }
    //删除
    @GetMapping("/del")
    public Result del(@RequestParam String id){
        return strategyService.removeById(id)?Result.suc():Result.fail();
    }

    @PostMapping("/listPage")
    public Result listPage(@RequestBody QueryPageParam query){
        HashMap param = query.getParam();
        String name = (String)param.get("name");

        Page<Strategy> page = new Page();
        page.setCurrent(query.getPageNum());
        page.setSize(query.getPageSize());

        LambdaQueryWrapper<Strategy> lambdaQueryWrapper = new LambdaQueryWrapper();
        if(StringUtils.isNotBlank(name) && !"null".equals(name)){
            lambdaQueryWrapper.like(Strategy::getName,name);
        }

        IPage result = strategyService.pageSelect(page,lambdaQueryWrapper);
        return Result.suc(result.getRecords(),result.getTotal());
    }

    @PostMapping("/upload")
    public Boolean upload(@RequestParam("file") MultipartFile file){

        boolean bool = false;
        String fileName = file.getOriginalFilename();
        try {
            bool = strategyUploadExcelUtil.batchImport(fileName, file);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return  bool;
    }
}
