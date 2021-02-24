package com.solidbeans.call4help.notification;

import com.solidbeans.call4help.entities.Location;
import lombok.*;

@Builder
@Getter
@EqualsAndHashCode
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class PlatformEndpointDTO {
    private String token;
    private Location location;
}
