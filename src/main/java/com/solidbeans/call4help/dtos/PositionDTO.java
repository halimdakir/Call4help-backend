package com.solidbeans.call4help.dtos;

import com.solidbeans.call4help.model.Coordinates;
import lombok.Data;

@Data
public class PositionDTO {
    private Coordinates coordinates;
    private String userId;
}
