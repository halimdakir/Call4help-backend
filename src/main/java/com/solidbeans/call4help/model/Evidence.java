package com.solidbeans.call4help.model;

import lombok.*;

@Builder
@Getter
@EqualsAndHashCode
@ToString
@AllArgsConstructor
public class Evidence {
    private String date;
    private int textQuantity;
    private int imageQuantity;
    private int videoQuantity;
}
