package com.solidbeans.call4help.entity;

import lombok.*;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@Setter
@Builder
@AllArgsConstructor
@Entity
@ToString
@EqualsAndHashCode
public class Endpoints {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    private String arn;


    @OneToOne(fetch = FetchType.LAZY, optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "position_id", nullable = false)
    private Position position;


    public Endpoints(String arn, Position position) {
        this.arn = arn;
        this.position = position;
    }
}
