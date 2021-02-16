package com.solidbeans.call4help.notification;

import com.amazonaws.services.sns.AmazonSNS;
import com.amazonaws.services.sns.model.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.solidbeans.call4help.exception.NotFoundException;

import java.util.HashMap;
import java.util.Map;

public class AmazonSNSClientWrapper {
    private static final ObjectMapper objectMapper = new ObjectMapper();

    private final AmazonSNS snsClient;

    public AmazonSNSClientWrapper(AmazonSNS client) {
        this.snsClient = client;
    }

    private CreatePlatformApplicationResult createPlatformApplication(String applicationName, String platform, String principal, String credential)
            throws AuthorizationErrorException, InternalErrorException, InvalidParameterException {

        CreatePlatformApplicationRequest createPlatformApplicationRequest = new CreatePlatformApplicationRequest();
        Map<String, String> attributes = new HashMap<String, String>();
        attributes.put("PlatformPrincipal", principal);
        attributes.put("PlatformCredential", credential);
        createPlatformApplicationRequest.setAttributes(attributes);
        createPlatformApplicationRequest.setName(applicationName);
        createPlatformApplicationRequest.setPlatform(platform);

        return snsClient.createPlatformApplication(createPlatformApplicationRequest);
    }

    /*public String createDeviceEndpoint(String customData, String token, String platformAppArn) throws AuthorizationErrorException, InternalErrorException, InvalidParameterException, NotFoundException {
        CreatePlatformEndpointResult result = new CreatePlatformEndpointResult();
        CreatePlatformEndpointRequest request = new CreatePlatformEndpointRequest();
        request.setCustomUserData(customData);
        request.setToken(token);
        request.setPlatformApplicationArn(platformAppArn);
        result  = snsClient.createPlatformEndpoint(request);
        return result.getEndpointArn();

    }

     */

    public PublishResult publish(String arn, Map<String, Map<String, String>> data) {
        String platform = "";
        if (data.keySet().iterator().hasNext()) {
            platform = data.keySet().iterator().next();
        }

        if (platform.equalsIgnoreCase("")) {
            throw new InvalidParameterException("Must be GCM");
        }

        PublishRequest request = new PublishRequest();
        request.setMessageStructure("json");
        Map<String, Object> androidMsg = new HashMap<String, Object>();
        Map<String, String> messageMap = new HashMap<String, String>();
        messageMap.put("text", data.get(platform).get("text"));
        androidMsg.put("data", messageMap);
        String message = jsonify(androidMsg);
        Map<String, String> msgMap = new HashMap<String, String>();
        msgMap.put("GCM", message);

        String sendMsg = jsonify(msgMap);
        request.setTargetArn(arn);
        request.setMessage(sendMsg);
        return snsClient.publish(request);
    }


    public static String jsonify(Object message) {
        try {
            return objectMapper.writeValueAsString(message);
        } catch (Exception e) {
            e.printStackTrace();
            throw (RuntimeException) e;
        }
    }

}
