package com.solidbeans.call4help.dto;


import lombok.*;

@Builder
@Getter
@EqualsAndHashCode
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class DistanceDTO {
    private Long id;
    private double st_distance;
}
