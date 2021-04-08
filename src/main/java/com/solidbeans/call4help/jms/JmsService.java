package com.solidbeans.call4help.jms;

import com.solidbeans.call4help.dtos.NotificationMessageDTO;

import java.util.List;

public interface JmsService {
    Object publishMessage(List<NotificationMessageDTO> list);
    List<MessageObject> messagesList(String userId);
    boolean findMessageByUuidAndRemove(String uuid);
}
