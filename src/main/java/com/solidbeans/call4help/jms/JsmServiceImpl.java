package com.solidbeans.call4help.jms;

import com.solidbeans.call4help.dtos.NotificationMessageDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class JsmServiceImpl implements JmsService{


    @Autowired
    private JmsTemplate jmsTemplate;



    @Override
    public String publishMessage(List<NotificationMessageDTO> list) {

        try {

            for (NotificationMessageDTO object:list){
                
                MessageObject messageObject = new MessageObject();
                messageObject.setHelper_id(object.getHelper_id());
                messageObject.setDistance(object.getDistance());
                messageObject.setSender_id(object.getSender_id());


                jmsTemplate.convertAndSend(JmsConfig.JMS_QUEUE, messageObject);

            }
            return "Successfully sent!";

        } catch (Exception e) {

            return e.getMessage();

        }
    }
}
