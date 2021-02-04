package com.solidbeans.call4help.entity;

import lombok.*;
import org.locationtech.jts.geom.Point;

import javax.persistence.*;
import java.time.ZonedDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "alam_position")
public class AlarmPosition {

    @EqualsAndHashCode.Exclude
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name="date_time")
    private ZonedDateTime dateTime;

    private org.locationtech.jts.geom.Point coordinates;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private Users users;

    public AlarmPosition(ZonedDateTime dateTime, Point coordinates, Users users) {
        this.dateTime = dateTime;
        this.coordinates = coordinates;
        this.users = users;
    }
}
