package com.solidbeans.call4help.jms;

import com.solidbeans.call4help.dtos.NotificationMessageDTO;

import java.util.List;

public interface JmsService {
    String publishMessage(List<NotificationMessageDTO> list);
}
