package com.solidbeans.call4help.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.sun.istack.NotNull;
import lombok.*;
import javax.persistence.*;
import java.time.ZonedDateTime;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Entity
public class Users {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String userId;
    private String authToken;

    @Column(name="latest_call_for_help_date")
    private ZonedDateTime latestCall4HelpDate;

    @Column(name="creation_date")
    private ZonedDateTime creationDate;

    @Column(name="token_update_date")
    private ZonedDateTime tokenUpdateDate;

    /*
    @JsonBackReference
    @OneToOne(fetch = FetchType.LAZY, cascade =  CascadeType.ALL, mappedBy = "user")
    private Position position;

    @JsonBackReference
    @OneToOne(fetch = FetchType.LAZY, cascade =  CascadeType.ALL, mappedBy = "user")
    private Shared shared;

    @JsonBackReference
    @OneToMany(mappedBy = "users", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Alert> alertSet;

     */


    public Users(String userId, String authToken, ZonedDateTime latestCall4HelpDate, ZonedDateTime creationDate, ZonedDateTime tokenUpdateDate) {
        this.userId = userId;
        this.authToken = authToken;
        this.latestCall4HelpDate = latestCall4HelpDate;
        this.creationDate = creationDate;
        this.tokenUpdateDate = tokenUpdateDate;
    }
}
