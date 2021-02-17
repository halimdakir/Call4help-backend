package com.solidbeans.call4help.notification;

import com.amazonaws.services.sns.AmazonSNS;
import com.amazonaws.services.sns.model.*;
import com.solidbeans.call4help.entity.Endpoints;
import com.solidbeans.call4help.entity.Position;
import com.solidbeans.call4help.exception.NotAcceptedException;
import com.solidbeans.call4help.exception.NotFoundException;
import com.solidbeans.call4help.repository.EndpointsRepository;
import com.solidbeans.call4help.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class AmazonSNSServiceImpl implements AmazonSNSService{

    @Autowired
    private EndpointsRepository endpointsRepository;
    @Autowired
    private UserService userService;
    private final AmazonSNS snsClient;
    @Value("${cloud.aws.platform.app.arn}")
    private String platform_App_Arn;


    public AmazonSNSServiceImpl(AmazonSNS snsClient) {
        this.snsClient = snsClient;
    }


    @Override
    public Endpoints createAwsSnsEndpoint(Position position) {
        var user = userService.getUserByPositionId(position.getId());

        if (user.isPresent()){

            var result =  createDeviceEndpoint(user.get().getAuthToken(), platform_App_Arn);

            if (result!=null){


                  var endpoints = new Endpoints(result, position);

                return endpointsRepository.save(endpoints);

            }else {
                throw new NotAcceptedException("Sorry! The endpoint is not created!");
            }

        }else {
            throw new NotFoundException("User is not found!");
        }

    }

    public String createDeviceEndpoint(String token, String platformAppArn) throws AuthorizationErrorException, InternalErrorException, InvalidParameterException, NotFoundException {
        CreatePlatformEndpointResult result = new CreatePlatformEndpointResult();
        CreatePlatformEndpointRequest request = new CreatePlatformEndpointRequest();
        request.setToken(token);
        request.setPlatformApplicationArn(platformAppArn);
        result  = snsClient.createPlatformEndpoint(request);
        return result.getEndpointArn();

    }
}
