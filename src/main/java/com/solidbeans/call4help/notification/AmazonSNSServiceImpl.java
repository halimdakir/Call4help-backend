package com.solidbeans.call4help.notification;

import com.amazonaws.services.sns.AmazonSNS;
import com.amazonaws.services.sns.model.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.solidbeans.call4help.entities.Endpoint;
import com.solidbeans.call4help.exception.NotAcceptedException;
import com.solidbeans.call4help.exception.NotFoundException;
import com.solidbeans.call4help.repository.EndpointsRepository;
import com.solidbeans.call4help.service.ProfileService;
import com.solidbeans.call4help.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
public class AmazonSNSServiceImpl implements AmazonSNSService{

    private final String platform_App_Arn = "platform_App_Arn";//arn:aws:sns:eu-west-1:364128993164:app/GCM/call4help";

    @Autowired
    private EndpointsRepository endpointsRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private ProfileService profileService;

    private final AmazonSNS snsClient;

    private static final ObjectMapper objectMapper = new ObjectMapper();



    public AmazonSNSServiceImpl(AmazonSNS snsClient) {
        this.snsClient = snsClient;
    }


    @Override
    public Endpoint createAwsSnsEndpoint(String userId) {
        var user = userService.findUserByUserId(userId);

        if (user != null){

            var profile = profileService.findProfileByUserId(user.getUserId());

            if (profile != null){

                var result =  createDeviceEndpoint(user.getAuthToken(), platform_App_Arn);

                if (result!=null){


                    var endpoints = new Endpoint(result, profile);

                    endpointsRepository.save(endpoints);

                    return endpoints;

                }else {
                    throw new NotAcceptedException("Sorry! The endpoint is not created!");
                }

            }else {
                throw new NotAcceptedException("Could not find profile with this is: "+userId);
            }


        }else {
            throw new NotFoundException("User is not found!");
        }

    }


    @Override
    public void publishMessage(List<Endpoint> endpointList, String message){
        if (endpointList.size() > 0){
            for (Endpoint endpoint: endpointList){
                if (endpoint.getArn() != null){
                    publish(endpoint.getArn(), message);
                }
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
        msgMap.put("GCM", message);

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
