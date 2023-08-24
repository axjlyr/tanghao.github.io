package com.wms.common;

import com.wms.mapper.UsersMapper;
import com.wms.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RandKami {

    @Autowired
    private UsersMapper usersMapper;

    //随机生成卡号
    public List<String> codeRandomCreator (int number)   {
        String code ="z";
        List<String> codeList = new ArrayList<>();
        String[] arr =new String[]{
                "0", "1", "2", "3", "5", "6", "7", "8", "9"
        };
        for (int i = 0; i < number; i++) {
            for (int j = 0; j <7 ; j++) {
                int round =(int) Math.round(Math.random() * (arr.length - 1));
                code += arr[round];
            }
            if (usersMapper.findByNo(code) ==null) {
                codeList.add(code);
            }else {
                number++;
            }
            code="z";
        }
        return codeList;
    }

    //随机生成卡密
    public List<String> codeRandomPassword (int number) {
        String code ="";
        List<String> codeList = new ArrayList<>();
        String[] arr =new String[]{
                "0", "1", "2", "3", "4", "5", "6", "7", "8", "9"
        };
        for (int i = 0; i < number; i++) {
            for (int j = 0; j <6 ; j++) {
                int round =(int) Math.round(Math.random() * (arr.length - 1));
                code += arr[round];
            }
            codeList.add(code);
            code="";
        }

        return codeList;
    }
}
