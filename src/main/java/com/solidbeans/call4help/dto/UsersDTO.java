package com.solidbeans.call4help.dto;

import lombok.*;

@Builder
@Getter
@EqualsAndHashCode
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class UsersDTO {
    private Long id;
    private String userId;
}
