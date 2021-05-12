package com.solidbeans.call4help.dtos;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReportDTO {
    private String text;
    private MultipartFile file;
}
