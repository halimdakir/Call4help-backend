package com.solidbeans.call4help.notification;

import com.solidbeans.call4help.entities.Endpoint;
import com.solidbeans.call4help.entities.Location;
import java.util.List;

public interface AmazonSNSService {

    Endpoint createAwsSnsEndpoint(Location location);

    void publishMessage(List<Endpoint> endpointList, String message);
}
