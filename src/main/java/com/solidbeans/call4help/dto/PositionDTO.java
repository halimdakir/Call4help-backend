package com.solidbeans.call4help.dto;

import com.solidbeans.call4help.entity.Users;
import lombok.*;

@Builder
@Getter
@EqualsAndHashCode
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class PositionDTO {
    private String municipality;
    private String userId;
}
