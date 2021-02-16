package com.solidbeans.call4help.notification;

import lombok.*;

@Builder
@Getter
@EqualsAndHashCode
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class PlatformEndpointDTO {

    private String customData;
    private String token;
    private String platformAppArn;

}
