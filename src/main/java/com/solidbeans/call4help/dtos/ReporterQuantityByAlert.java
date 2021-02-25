package com.solidbeans.call4help.dtos;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.ZonedDateTime;


@Data
@Getter
@Setter
public class ReporterQuantityByAlert {
    private Long id;
    private ZonedDateTime alertDate;
    private String location;
    private Long reporter;

    public ReporterQuantityByAlert(Long id, ZonedDateTime alertDate, String location, Long reporter) {
        this.id = id;
        this.alertDate = alertDate;
        this.location = location;
        this.reporter = reporter;
    }
}
