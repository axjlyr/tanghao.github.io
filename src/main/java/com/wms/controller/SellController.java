package com.wms.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import java.util.List;
import com.wms.common.GradeUploadExcelUtil;
import com.wms.common.QueryPageParam;
import com.wms.common.Result;
import com.wms.entity.Grade;
import com.wms.entity.Homepage;
import com.wms.entity.Sell;
import com.wms.service.GradeService;
import com.wms.service.HomepageService;
import com.wms.service.SellService;
import com.wms.service.impl.GradeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.HashMap;

@RestController
@RequestMapping("/sell")
public class SellController {

    @Autowired
    private SellService sellService;
    @Autowired
    private GradeService gradeService;
    @Autowired
    private HomepageService homepageService;
    @Autowired
    private GradeUploadExcelUtil gradeUploadExcelUtil;

    @PostMapping("/save")
    public Result save(@RequestBody Sell sell){
        return sellService.save(sell)?Result.suc():Result.fail();
    }
    //更新
    @PostMapping("/update")
    public Result update(@RequestBody Sell sell){
        return sellService.updateById(sell)?Result.suc():Result.fail();
    }
    //删除
    @GetMapping("/del")
    public Result del(@RequestParam String id){
        return sellService.removeById(id)?Result.suc():Result.fail();
    }

    @PostMapping("/listPage")
    public Result listPage(@RequestBody QueryPageParam query){
        HashMap param = query.getParam();
        String type = (String)param.get("type");

        Page<Sell> page = new Page();
        page.setCurrent(query.getPageNum());
        page.setSize(query.getPageSize());

        LambdaQueryWrapper<Sell> lambdaQueryWrapper = new LambdaQueryWrapper();
        if(StringUtils.isNotBlank(type) && !"null".equals(type)){
            lambdaQueryWrapper.like(Sell::getId,type);
        }


        IPage result = sellService.pageSelect(page,lambdaQueryWrapper);
        return Result.suc(result.getRecords(),result.getTotal());
    }

    @PostMapping("uploadGrade")
    public Boolean uploadGrade(@RequestParam("file") MultipartFile file){

        boolean bool = false;
        String fileName = file.getOriginalFilename();
        try {
            bool = gradeUploadExcelUtil.batchImport(fileName, file);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return  bool;
    }


    @PostMapping("/listSell")
    public Result listSell(@RequestBody QueryPageParam query){
        Sell sell=new Sell();
        HashMap param = query.getParam();
        String types = (String)param.get("types");
        String majorBatch = (String) param.get("majorBatch");
        Integer up = (Integer) param.get("up");
        Integer down = (Integer) param.get("down");
        Integer ranking1;
        Integer ranking2;
        String type;
        if (types.equals("10") || types.equals("11")){
            type = "文史";
        }else if (types.equals("00") || types.equals("01")){
            type="理工";
        }else {
            return Result.fail();
        }
        int max= Integer.parseInt(gradeService.queryMax(type).getMark());
        int min= Integer.parseInt(gradeService.queryMin(type).getMark());
        Page<Homepage> page = new Page();
        page.setCurrent(query.getPageNum());
        page.setSize(query.getPageSize());

        sell.setTypes(types);
        String grade = sellService.pageSell(sell);
        int i= Integer.parseInt(grade);
        up=i+up;
        down=i-down;
        if (up>=max) {
            ranking1 = Integer.valueOf(gradeService.queryfromMark(max,type).getRanking());
        }else {
            ranking1 = Integer.valueOf(gradeService.queryfromMark(up,type).getRanking());
        }
        if (down<=min){
            ranking2 = Integer.valueOf(gradeService.queryfromMark(min,type).getRanking());
        }else {
            ranking2 = Integer.valueOf(gradeService.queryfromMark(down,type).getRanking());
        }

        IPage result = homepageService.pagePlace(page,ranking1,ranking2,majorBatch,type);
        return Result.suc(result.getRecords(),result.getTotal());
    }


    @PostMapping("/listRanking")
    public Result listRanking(@RequestBody QueryPageParam query){

        HashMap param = query.getParam();
        Sell sell=new Sell();
        Integer mark = (Integer) param.get("mark");
        Integer up = (Integer) param.get("up");
        Integer down = (Integer) param.get("down");
        if (param.get("types")!=null){
            String type =null;
            String types = (String)param.get("types");
            if (types.equals("10") || types.equals("11")){
                type = "文史";
            }else if (types.equals("00") || types.equals("01")){
                type ="理工";
            }
            int max= Integer.parseInt(gradeService.queryMax(type).getMark());
            gradeService.queryMax(type);
            sell.setTypes(types);
            String grade1 = sellService.pageSell(sell);
            int i= Integer.parseInt(grade1);
            int ranking;
            up=i+up;
            if (up>=max) {
                ranking = Integer.valueOf(gradeService.queryfromMark(max,type).getRanking());
            }else {
                ranking = Integer.valueOf(gradeService.queryfromMark(up,type).getRanking());
            }
            return Result.suc(ranking);
        }else {
            String type = (String) param.get("type");
            up=mark+up;
            int max= Integer.parseInt(gradeService.queryMax(type).getMark());
            int min= Integer.parseInt(gradeService.queryMin(type).getMark());
            int ranking1;
            int ranking2;
            Grade grade = new Grade();
            List list = new ArrayList<>();
            if (up>=max) {
                ranking1 = Integer.valueOf(gradeService.queryfromMark(max,type).getRanking());
            }else {
                ranking1 = Integer.valueOf(gradeService.queryfromMark(up,type).getRanking());
            }
            if (down<=min) {
                ranking2 = Integer.valueOf(gradeService.queryfromMark(min   ,type).getRanking());
            }else {
                ranking2 = Integer.valueOf(gradeService.queryfromMark(down,type).getRanking());
            }
            list.add(ranking1);
            list.add(ranking2);
            return Result.suc(list);
        }
    }

    @PostMapping("/listAll")
    public Result listAll(@RequestBody QueryPageParam query) {

        HashMap param = query.getParam();
        Integer mark = (Integer) param.get("mark");
        Integer up = (Integer) param.get("up");
        String majorBatch = (String) param.get("majorBatch");
        Integer down = (Integer) param.get("down");
        String type = (String) param.get("type");
        int max= Integer.parseInt(gradeService.queryMax(type).getMark());
        int min= Integer.parseInt(gradeService.queryMin(type).getMark());
        int ranking1;
        int ranking2;

        Page<Homepage> page = new Page();
        page.setCurrent(query.getPageNum());
        page.setSize(query.getPageSize());

        up = mark + up;

        if (up>=max) {
            ranking1 = Integer.valueOf(gradeService.queryfromMark(max,type).getRanking());
        }else {
            ranking1 = Integer.valueOf(gradeService.queryfromMark(up,type).getRanking());
        }
        down = mark - down;
        if (down<=min){
            ranking2 = Integer.valueOf(gradeService.queryfromMark(min,type).getRanking());
        }else {
            ranking2 = Integer.valueOf(gradeService.queryfromMark(down,type).getRanking());
        }
        IPage result = homepageService.pagePlace(page,ranking1,ranking2,majorBatch,type);
        return Result.suc(result.getRecords(),result.getTotal());

    }
}
