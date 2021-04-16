package com.solidbeans.call4help.controllers;

import com.solidbeans.call4help.service.AppInfoService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/v1", produces = MediaType.APPLICATION_JSON_VALUE)
public class AppInfoController {

    private final AppInfoService appInfoService;

    public AppInfoController(AppInfoService appInfoService) {
        this.appInfoService = appInfoService;
    }

    @GetMapping("auth/info")
    public ResponseEntity<?> getAppInfos(@RequestHeader("X-Auth-Token") String token, @RequestHeader("X-Auth-User") String userId) {
        return appInfoService.getAppInfo();
    }
}
