package com.solidbeans.call4help.entity;

import lombok.*;

import javax.persistence.*;
import java.time.ZonedDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Entity
public class Alert {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    @Column(name="alert_date")
    private ZonedDateTime alertDate;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private Users users;

    public Alert(ZonedDateTime alertDate, Users users) {
        this.alertDate = alertDate;
        this.users = users;
    }
}
