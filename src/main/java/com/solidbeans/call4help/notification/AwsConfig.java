package com.solidbeans.call4help.notification;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.sns.AmazonSNSClient;
import com.amazonaws.services.sns.AmazonSNSClientBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class AwsConfig {

    //@Value("${cloud.aws.credentials.access-key}")
    private final String accessKey = "accessKey";
    //@Value("${cloud.aws.credentials.secret-key}")
    private final String secretKey = "/secretKey";
    //@Value("${cloud.aws.region.static}")
    private final String region = "EU_WEST_1";

    @Primary
    @Bean
    public AmazonSNSClient getSnsClient(){
        return (AmazonSNSClient) AmazonSNSClientBuilder.standard().withRegion(Regions.EU_WEST_1)
                .withCredentials(new AWSStaticCredentialsProvider(new BasicAWSCredentials(accessKey, secretKey)))
                .build();
    }
}
