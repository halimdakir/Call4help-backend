package com.solidbeans.call4help.notification;

import com.solidbeans.call4help.entities.Endpoint;

import java.util.List;

public interface AmazonSNSService {

    Endpoint createAwsSnsEndpoint(String userId);

    void publishMessage(List<Endpoint> endpointList, String message);
}
