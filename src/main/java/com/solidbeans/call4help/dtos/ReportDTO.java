package com.solidbeans.call4help.dtos;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.Setter;

@Data
@Builder
public class ReportDTO {

    @Setter(value= AccessLevel.NONE)
    private String text;
    private Long alertId;
}
