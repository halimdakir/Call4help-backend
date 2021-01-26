package com.solidbeans.call4help.service;

import com.solidbeans.call4help.model.AppInfo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;


@Service
public class AppInfoService {

    @Value("${app.version}")
    private String appVersion;
    @Value("${app.name}")
    private String appName;



    public AppInfo getAppInfo(){
        return new AppInfo(appVersion, appName);
    }
}
