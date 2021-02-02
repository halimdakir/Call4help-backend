package com.solidbeans.call4help.entity;

import lombok.*;

import javax.persistence.*;
import java.time.ZonedDateTime;

@NoArgsConstructor
@Getter
@Setter
@Builder
@AllArgsConstructor
@Entity
@ToString
@EqualsAndHashCode
public class Coordinates {
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
}
