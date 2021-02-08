package com.solidbeans.call4help.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.solidbeans.call4help.entity.Users;
import lombok.*;

import java.time.ZonedDateTime;

@Builder
@Getter
@EqualsAndHashCode
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class AlertDTO {
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSX")
    private ZonedDateTime alertDate;
    private Users users;
}
