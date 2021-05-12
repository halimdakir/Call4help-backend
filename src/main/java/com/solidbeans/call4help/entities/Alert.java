package com.solidbeans.call4help.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.locationtech.jts.geom.Point;

import javax.persistence.*;
import java.time.ZonedDateTime;
import java.util.Set;

@Data
@NoArgsConstructor
@Entity
public class Alert {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column(name="start_alert_date")
    private ZonedDateTime startAlertDate;

    @Column(name="end_alert_date")
    private ZonedDateTime endAlertDate;
    private org.locationtech.jts.geom.Point coordinates;

    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private Users users;

    @JsonBackReference
    @OneToMany(mappedBy = "alert", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Report> reports;

    @JsonBackReference
    @OneToMany(mappedBy = "alert", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Images> images;

    @JsonBackReference
    @OneToMany(mappedBy = "alert", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Videos> videos;

    public Alert(ZonedDateTime startAlertDate, ZonedDateTime endAlertDate, Point coordinates, Users users) {
        this.startAlertDate = startAlertDate;
        this.endAlertDate = endAlertDate;
        this.coordinates = coordinates;
        this.users = users;
    }
}
