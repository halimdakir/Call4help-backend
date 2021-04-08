package com.solidbeans.call4help.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
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

    @Column(name="alert_date")
    private ZonedDateTime alertDate;

    private org.locationtech.jts.geom.Point coordinates;

    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private Users users;

    @JsonBackReference
    @OneToMany(mappedBy = "alert", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Report> reports;

    public Alert(ZonedDateTime alertDate, Point coordinates, Users users) {
        this.alertDate = alertDate;
        this.coordinates = coordinates;
        this.users = users;
    }
}
