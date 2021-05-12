package com.solidbeans.call4help.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;


@Data
@AllArgsConstructor
public class ActiveAlertDTO {
    private Long id;
    private String startAlertDate;
    private String startAlertTime;
    private String endAlertDate;
    private String endAlertTime;
    private String userId;

}
