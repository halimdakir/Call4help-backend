package com.solidbeans.call4help.entity;

import com.sun.istack.NotNull;
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

    @NotNull
    private String userId;

    @Column(name="alert_date")
    private ZonedDateTime alertDate;

    /*
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private Users user;

     */

    public Alert(String userId, ZonedDateTime alertDate) {
        this.userId = userId;
        this.alertDate = alertDate;
    }
}
