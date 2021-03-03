package com.solidbeans.call4help.jms;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class JmsConsumer {

    public static List<MessageObject> list = new ArrayList<>();
    private static final Logger LOGGER = LoggerFactory.getLogger(JmsConsumer.class);


    @JmsListener(destination = JmsConfig.JMS_QUEUE)
    public void messageListener(MessageObject message) {
        LOGGER.info("Message received! {}", message);
        list.add(message);
    }

}
