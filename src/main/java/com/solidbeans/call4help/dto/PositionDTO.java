package com.solidbeans.call4help.dto;

import com.solidbeans.call4help.entity.Users;
import lombok.*;
import java.time.ZonedDateTime;

@Builder
@Getter
@EqualsAndHashCode
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class PositionDTO {
    private ZonedDateTime dateTime;
    private Coordinates coordinates;
    private Users user;
}
