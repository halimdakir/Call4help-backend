package com.solidbeans.call4help.jms;

import com.solidbeans.call4help.dtos.NotificationMessageDTO;
import com.solidbeans.call4help.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;


@Service
public class JsmServiceImpl implements JmsService{


    @Autowired
    private JmsTemplate jmsTemplate;



    @Override
    public Object publishMessage(List<NotificationMessageDTO> list) {
        MessageObject messageObject = new MessageObject();
        try {

            for (NotificationMessageDTO object:list){
                

                messageObject.setHelper_id(object.getHelper_id());
                messageObject.setDistance(object.getDistance());
                messageObject.setSender_id(object.getSender_id());


                jmsTemplate.convertAndSend(JmsConfig.JMS_QUEUE, messageObject);

            }
            return messageObject;

        } catch (Exception e) {

            return e.getMessage();

        }
    }

    @Override
    public List<MessageObject> messagesList(Long helper_Id) {
        return JmsConsumer.Jms_Message_List.stream()
                .filter(e -> e.getHelper_id().equals(helper_Id))
                .collect(Collectors.toList());
    }

    @Override
    public boolean findMessageByUuidAndRemove(String uuid) {
        UUID stringToUuid = UUID.fromString(uuid);
        var message = JmsConsumer.Jms_Message_List.stream()
                .filter(e -> e.getUuid().equals(stringToUuid))
                .collect(Collectors.toList());
        if (message.size()>1){
            return JmsConsumer.Jms_Message_List.remove(message.get(0));
        }
        else {
            throw new NotFoundException("Message with uuid :"+uuid+" Not found!");
        }

    }
}
