package com.solidbeans.call4help.dto;

import lombok.*;


@Builder
@Getter
@EqualsAndHashCode
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {

    private String userId;
    private String authToken;

}
