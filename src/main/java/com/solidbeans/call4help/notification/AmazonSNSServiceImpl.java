package com.solidbeans.call4help.notification;

import com.amazonaws.services.sns.AmazonSNS;
import com.amazonaws.services.sns.model.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.solidbeans.call4help.entity.Endpoints;
import com.solidbeans.call4help.entity.Position;
import com.solidbeans.call4help.exception.NotAcceptedException;
import com.solidbeans.call4help.exception.NotFoundException;
import com.solidbeans.call4help.repository.EndpointsRepository;
import com.solidbeans.call4help.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
public class AmazonSNSServiceImpl implements AmazonSNSService{

    @Value("${cloud.aws.platform.app.arn}")
    private String platform_App_Arn;

    @Autowired
    private EndpointsRepository endpointsRepository;

    @Autowired
    private UserService userService;

    private final AmazonSNS snsClient;

    private static final ObjectMapper objectMapper = new ObjectMapper();



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


    @Override
    public void publishMessage(List<Endpoints> endpointsList, String message){
        if (endpointsList.size() > 0){
            for (Endpoints endpoint: endpointsList){
                publish(endpoint.getArn(), message);
            }
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

    public void publish(String arn, String message) {
        PublishRequest request = new PublishRequest();
        request.setMessageStructure("json");
        Map<String, String> msgMap = new HashMap<>();
        msgMap.put("FCM", message);

        String sendMsg = jsonify(msgMap);
        request.setTargetArn(arn);
        request.setMessage(sendMsg);
        snsClient.publish(request);
    }


    public static String jsonify(Object message) {
        try {
            return objectMapper.writeValueAsString(message);
        } catch (Exception e) {
            e.printStackTrace();
            assert e instanceof RuntimeException;
            throw (RuntimeException) e;
        }
    }
}
