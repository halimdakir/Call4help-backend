package com.solidbeans.call4help.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class NotificationMessageDTO {
    private Long helper_id;
    private int distance;
    private Long sender_id;
}