package com.solidbeans.call4help.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class NotificationMessageDTO {
    private Long alert_id;
    private Long helper_id;
    private String distance;
    private Long sender_id;
}