package com.solidbeans.call4help.notification;

import com.solidbeans.call4help.entity.Endpoints;
import com.solidbeans.call4help.entity.Position;

public interface AmazonSNSService {
    Endpoints createAwsSnsEndpoint(Position position);
}
