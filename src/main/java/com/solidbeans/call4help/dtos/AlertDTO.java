package com.solidbeans.call4help.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.solidbeans.call4help.entities.Users;
import lombok.*;

import java.time.ZonedDateTime;

@Data
public class AlertDTO {

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSX")
    private ZonedDateTime alertDate;

    private Users users;
}
