package com.wms.controller;

import com.wms.common.Result;
import com.wms.entity.SearchInfo;
import com.wms.service.AdmissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.PublicKey;

@RestController
@RequestMapping("/search")
public class SearchController {

    @Autowired
    private AdmissionService admission;

//    @PostMapping("/pici")
//    public Result searchInfo(@RequestBody SearchInfo searchInfo){
//
//
//
//        return
//    }

}
