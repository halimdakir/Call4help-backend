package com.solidbeans.call4help.notification;

import com.amazonaws.services.sns.AmazonSNS;
import com.amazonaws.services.sns.model.*;
import com.solidbeans.call4help.exception.NotAcceptedException;
import com.solidbeans.call4help.exception.NotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AmazonSNSServiceImpl implements AmazonSNSService{

    private final AmazonSNS snsClient;

    public AmazonSNSServiceImpl(AmazonSNS snsClient) {
        this.snsClient = snsClient;
    }


    @Override
    public String createAwsSnsEndpoint(PlatformEndpointDTO platformEndpointDTO) {
        var result =  createDeviceEndpoint(platformEndpointDTO.getCustomData(),
                platformEndpointDTO.getToken(), platformEndpointDTO.getPlatformAppArn());
        if (result!=null){
            return result;
        }else {
            throw new NotAcceptedException("Sorry! The endpoint is not created!");
        }
    }

    public String createDeviceEndpoint(String customData, String token, String platformAppArn) throws AuthorizationErrorException, InternalErrorException, InvalidParameterException, NotFoundException {
        CreatePlatformEndpointResult result = new CreatePlatformEndpointResult();
        CreatePlatformEndpointRequest request = new CreatePlatformEndpointRequest();
        request.setCustomUserData(customData);
        request.setToken(token);
        request.setPlatformApplicationArn(platformAppArn);
        result  = snsClient.createPlatformEndpoint(request);
        return result.getEndpointArn();

    }
}
