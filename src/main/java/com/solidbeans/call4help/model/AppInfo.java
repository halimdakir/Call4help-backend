package com.solidbeans.call4help.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AppInfo {

    @Value("${app.version}")
    private String appVersion;
    @Value("${app.name}")
    private String appName;
}
