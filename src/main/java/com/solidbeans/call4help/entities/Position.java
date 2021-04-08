package com.solidbeans.call4help.entities;


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
public class Position {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private ZonedDateTime time;

    private org.locationtech.jts.geom.Point coordinates;


    @OneToOne(fetch = FetchType.LAZY, optional = false, cascade = CascadeType.DETACH)
    @JoinColumn(name = "profile_id", nullable = false)
    private Profile profile;

    public Position(ZonedDateTime time, Point coordinates, Profile profile) {
        this.time = time;
        this.coordinates = coordinates;
        this.profile = profile;
    }
}
