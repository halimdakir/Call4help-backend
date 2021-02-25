package com.solidbeans.call4help.dtos;

import java.time.ZonedDateTime;


public class ReporterQuantityByAlert {
    private Long id;
    private ZonedDateTime alertDate;
    private String location;
    private Long reporter;

    public ReporterQuantityByAlert() {
    }

    public ReporterQuantityByAlert(Long id, ZonedDateTime alertDate, String location, Long reporter) {
        this.id = id;
        this.alertDate = alertDate;
        this.location = location;
        this.reporter = reporter;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ZonedDateTime getAlertDate() {
        return alertDate;
    }

    public void setAlertDate(ZonedDateTime alertDate) {
        this.alertDate = alertDate;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Long getReporter() {
        return reporter;
    }

    public void setReporter(Long reporter) {
        this.reporter = reporter;
    }
}
