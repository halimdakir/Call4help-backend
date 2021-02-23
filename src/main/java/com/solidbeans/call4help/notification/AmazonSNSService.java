package com.solidbeans.call4help.notification;

import com.solidbeans.call4help.entities.Endpoint;
import com.solidbeans.call4help.entities.Position;
import java.util.List;

public interface AmazonSNSService {

    Endpoint createAwsSnsEndpoint(Position position);

    void publishMessage(List<Endpoint> endpointList, String message);
}
