package com.solidbeans.call4help.dtos;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Data
@Builder
public class ReportDTO {

    @Setter(value= AccessLevel.NONE)
    private String text;
    private MultipartFile file;
    private Long alertId;
}
