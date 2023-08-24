package com.wms.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wms.common.QueryPageParam;
import com.wms.common.Result;
import com.wms.entity.Sort;
import com.wms.service.SortService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
@RequestMapping("/sort")
public class SortController {

    @Autowired
    private SortService sortService;

    @PostMapping("/save")
    public Result save(@RequestBody Sort sort){
        return sortService.save(sort)?Result.suc():Result.fail();
    }
    //更新
    @PostMapping("/update")
    public Result update(@RequestBody Sort sort){
        return sortService.updateById(sort)?Result.suc():Result.fail();
    }
    //删除
    @GetMapping("/del")
    public Result del(@RequestParam String id){
        return sortService.removeById(id)?Result.suc():Result.fail();
    }

    @PostMapping("/listPage")
    public Result listPage(@RequestBody QueryPageParam query){
        HashMap param = query.getParam();
        String school = (String)param.get("school");

        Page<Sort> page = new Page();
        page.setCurrent(query.getPageNum());
        page.setSize(query.getPageSize());

        LambdaQueryWrapper<Sort> lambdaQueryWrapper = new LambdaQueryWrapper();
        if(StringUtils.isNotBlank(school) && !"null".equals(school)){
            lambdaQueryWrapper.like(Sort::getSchool,school);
        }


        IPage result = sortService.pageSelect(page,lambdaQueryWrapper);
        return Result.suc(result.getRecords(),result.getTotal());
    }
}
