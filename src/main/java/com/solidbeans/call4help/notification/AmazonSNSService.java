package com.solidbeans.call4help.notification;

import com.amazonaws.services.sns.model.PublishResult;
import com.solidbeans.call4help.entity.Endpoints;
import com.solidbeans.call4help.entity.Position;

import java.util.List;

public interface AmazonSNSService {
    Endpoints createAwsSnsEndpoint(Position position);
    void publishMessage(List<Endpoints> endpointsList, String message);
}
