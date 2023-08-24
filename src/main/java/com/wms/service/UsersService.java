package com.wms.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.wms.entity.Users;

public interface UsersService extends IService<Users> {
    IPage pageSelect(IPage<Users> page, Wrapper wrapper);

    String findByNo(String no);

    void addUsers(Users sysUsers);
    // 当数据库中有对应姓名记录时，更新
    void updateUsers(Users sysUsers);

    //根据No删除用户
    Boolean deleteFromNo(String no);

    //根据时间删除用户
    Boolean deleteFromBatche(String batche);
}
