package com.solidbeans.call4help.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.sun.istack.NotNull;
import lombok.*;
import javax.persistence.*;
import java.time.ZonedDateTime;
import java.util.Set;

@Data
@NoArgsConstructor
@Entity
public class Users {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String userId;
    private String authToken;

    @Column(name="creation_date")
    private ZonedDateTime creationDate;

    @Column(name="token_update_date")
    private ZonedDateTime tokenUpdateDate;


    @JsonBackReference
    @OneToOne(fetch = FetchType.LAZY, cascade =  CascadeType.ALL, mappedBy = "users")
    private Position position;

    @JsonBackReference
    @OneToOne(fetch = FetchType.LAZY, cascade =  CascadeType.ALL, mappedBy = "users")
    private Report report;

    @JsonBackReference
    @OneToMany(mappedBy = "users", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Alert> alertSet;

    /*@JsonBackReference
    @OneToOne(fetch = FetchType.LAZY, cascade =  CascadeType.ALL, mappedBy = "user")
    private Shared shared;*/

    public Users(String userId, String authToken, ZonedDateTime creationDate, ZonedDateTime tokenUpdateDate) {
        this.userId = userId;
        this.authToken = authToken;
        this.creationDate = creationDate;
        this.tokenUpdateDate = tokenUpdateDate;
    }
}
