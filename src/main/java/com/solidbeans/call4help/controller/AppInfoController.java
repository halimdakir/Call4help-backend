package com.solidbeans.call4help.controller;
import com.solidbeans.call4help.model.AppInfo;
import com.solidbeans.call4help.service.AppInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/v1/auth")
public class AppInfoController {

    @Autowired
    AppInfoService appInfoService;


    @GetMapping("/info")
    public AppInfo getAppInfos(){
        return appInfoService.getAppInfo();
    }

}
