package com.solidbeans.call4help.entities;

import lombok.*;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity
public class Endpoint {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    private String arn;

    @OneToOne(fetch = FetchType.LAZY, optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "location_id", nullable = false)
    private Location location;

    public Endpoint(String arn, Location location) {
        this.arn = arn;
        this.location = location;
    }
}
