package com.solidbeans.call4help.service;

import com.solidbeans.call4help.model.AppInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class AppInfoService {

    @Autowired
    private AppInfo appInfo;

    public ResponseEntity<?> getAppInfo() {
        return ResponseEntity.ok(new AppInfo(appInfo.getAppVersion(), appInfo.getAppName()));
    }
}
