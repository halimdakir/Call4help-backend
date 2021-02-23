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
    @JoinColumn(name = "position_id", nullable = false)
    private Position position;

    public Endpoint(String arn, Position position) {
        this.arn = arn;
        this.position = position;
    }
}
