package com.solidbeans.call4help.dto;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
public class ReportDTO {
    @Setter(value= AccessLevel.NONE)
    private String text;
}
