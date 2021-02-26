package com.solidbeans.call4help.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class NotificationMessageDTO {
    private Long id;
    private String userId;
    private int distance;
}