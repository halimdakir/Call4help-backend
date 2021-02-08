package com.solidbeans.call4help.model;

import lombok.*;

@Builder
@Getter
@EqualsAndHashCode
@ToString
@AllArgsConstructor
public class Coordinates {
    private Double lng;
    private Double lat;
}
