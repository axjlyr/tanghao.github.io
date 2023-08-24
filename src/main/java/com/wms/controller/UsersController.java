package com.wms.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wms.common.QueryPageParam;
import com.wms.common.RandKami;
import com.wms.common.Result;
import com.wms.common.UsersUploadExcelUtil;
import com.wms.entity.Menu;
import com.wms.entity.Users;
import com.wms.service.MenuService;
import com.wms.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UsersController {

    @Autowired
    private UsersService usersService;

    @Autowired
    private MenuService menuService;

    @Autowired
    private RandKami randKami;

    @Autowired
    private UsersUploadExcelUtil usersUploadExcelUtil;

    @PostMapping("/save")
    public Result save(@RequestBody QueryPageParam query){

        HashMap param = query.getParam();
        String i = (String) param.get("number");
        String batch= (String) param.get("no");
        String strategy = (String) param.get("strategy");

        List<String> nos= randKami.codeRandomCreator(Integer.parseInt(i));
        List<String> passowrds = randKami.codeRandomPassword(Integer.parseInt(i));

        for (int j=0;j<nos.toArray().length;j++){
            Users users =new Users();
            users.setNo(nos.get(j));
            users.setPassword(passowrds.get(j));
            users.setBatche(batch);
            users.setStrategy(strategy);

            if (!(usersService.save(users))){
                return Result.fail();
            }
        }
        return Result.suc();
    }
    //更新
    @PostMapping("/update")
    public Result update(@RequestBody Users users){
        return usersService.updateById(users)?Result.suc():Result.fail();
    }
    //删除
    @GetMapping("/del")
    public Result del(@RequestParam String id){
        return usersService.removeById(id)?Result.suc():Result.fail();
    }


    @GetMapping("/findByNo")
    public Result findByNo(@RequestParam String no){
        List list = usersService.lambdaQuery().eq(Users::getNo,no).list();
        return list.size()>0?Result.suc(list):Result.fail();
    }

    @PostMapping("/listPageC1")
    public Result listPage(@RequestBody QueryPageParam query){
        HashMap param = query.getParam();
        String no = (String)param.get("no");

        Page<Users> page = new Page();
        page.setCurrent(query.getPageNum());
        page.setSize(query.getPageSize());

        LambdaQueryWrapper<Users> lambdaQueryWrapper = new LambdaQueryWrapper();
        if(StringUtils.isNotBlank(no) && !"null".equals(no)){
            lambdaQueryWrapper.like(Users::getNo,no);
        }

        IPage result = usersService.pageSelect(page,lambdaQueryWrapper);
        return Result.suc(result.getRecords(),result.getTotal());
    }

    @PostMapping("/login")
    public Result login(@RequestBody Users users, HttpServletRequest request){
        List list = usersService.lambdaQuery()
                .eq(Users::getNo,users.getNo())
                .eq(Users::getPassword,users.getPassword()).list();

        if(list.size()>0){
            Users users1 = (Users)list.get(0);
            List menuList = menuService.lambdaQuery().like(Menu::getMenuright,users1.getRole()).list();
            HashMap res = new HashMap();
            res.put("users",users1);
            res.put("menu",menuList);
            request.getSession().setAttribute("loginUser",users1.getName());
            Cookie[] cookies = request.getCookies();
            String userName = "";
            return Result.suc(res);
        }
        return Result.fail();
    }

    @PostMapping("logout")
    public Boolean logout(@RequestBody HttpServletRequest request) {
        Boolean bool = false;
        request.getSession().invalidate();
        if (request.getSession().getAttribute("loginUser") == null) {
            bool = true;
        }
        return bool;
    }

    @PostMapping("/upload")
    public Boolean upload(@RequestParam("file") MultipartFile file){

        boolean bool = false;
        String fileName = file.getOriginalFilename();
        try {
            bool = usersUploadExcelUtil.batchImport(fileName, file);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return  bool;
    }

    @GetMapping("/deleteFromNo")
    public Boolean deleteFromNo(@RequestParam String no){
        return usersService.deleteFromNo(no);
    }

    @GetMapping("/deleteFromBatche")
    public Boolean deleteFromBatche(@RequestParam String batche){
        return usersService.deleteFromBatche(batche);
    }
}
