package com.solidbeans.call4help.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.sun.istack.NotNull;
import lombok.*;
import org.locationtech.jts.geom.Point;

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
public class Position {
    @EqualsAndHashCode.Exclude
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="date_time")
    private ZonedDateTime dateTime;

    private String municipality;

    //private org.locationtech.jts.geom.Point coordinates;

    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @JsonManagedReference
    @OneToOne(fetch = FetchType.LAZY, optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", nullable = false)
    private Users user;

    public Position(ZonedDateTime dateTime, String municipality, Users user) {
        this.dateTime = dateTime;
        this.municipality = municipality;
        this.user = user;
    }
}
