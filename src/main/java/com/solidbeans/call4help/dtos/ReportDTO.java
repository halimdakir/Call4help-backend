package com.solidbeans.call4help.dtos;

import lombok.*;

@Data
@Builder
public class ReportDTO {

    @Setter(value= AccessLevel.NONE)
    private String text;
    private Long alertId;
}
