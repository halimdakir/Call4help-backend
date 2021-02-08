package com.solidbeans.call4help.dto;


import lombok.*;


@Builder
@Getter
@EqualsAndHashCode
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class DistanceToMeters {
    private Long id;
    private double distance;
}
