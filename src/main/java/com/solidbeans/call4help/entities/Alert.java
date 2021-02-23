package com.solidbeans.call4help.entities;

import lombok.*;

import javax.persistence.*;
import java.time.ZonedDateTime;

@Data
@NoArgsConstructor
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
