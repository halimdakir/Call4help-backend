package com.solidbeans.call4help.jms;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
public class JmsConsumer {

    public static List<MessageObject> Jms_Message_List = new ArrayList<>();
    private static final Logger LOGGER = LoggerFactory.getLogger(JmsConsumer.class);

    JmsConsumer(){
        Jms_Message_List.add(new MessageObject(UUID.fromString("c024c94d-d39e-488f-bd1e-9cd2badb1cb2"), 1L, 5L, "55 meter bort", 1L));
        //Jms_Message_List.add(new MessageObject(UUID.fromString("c024c94d-d39e-488f-bd1e-9cd2badb1cb1"), 2L, 5L, "252 meter bort", 3L));
    }


    @JmsListener(destination = JmsConfig.JMS_QUEUE)
    public void messageListener(MessageObject message) {
        LOGGER.info("Message received! {}", message);
        Jms_Message_List.add(message);
    }

}
