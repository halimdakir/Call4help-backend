package com.solidbeans.call4help.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.sun.istack.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

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

    //private String locale;

    @JsonBackReference
    @OneToOne(fetch = FetchType.LAZY, cascade =  CascadeType.ALL, mappedBy = "users")
    private Profile profile;

    @JsonBackReference
    @OneToMany(fetch = FetchType.LAZY, cascade =  CascadeType.ALL, mappedBy = "users")
    private Set<Images> imagesSet;

    @JsonBackReference
    @OneToMany(fetch = FetchType.LAZY, cascade =  CascadeType.ALL, mappedBy = "users")
    private Set<Videos> videosSet;

    @JsonBackReference
    @OneToMany(fetch = FetchType.LAZY, cascade =  CascadeType.ALL, mappedBy = "users")
    private Set<Report> reportSet;

    @JsonBackReference
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "users")
    private Set<Alert> alertSet;


    public Users(String userId, String authToken, ZonedDateTime creationDate, ZonedDateTime tokenUpdateDate) {
        this.userId = userId;
        this.authToken = authToken;
        this.creationDate = creationDate;
        this.tokenUpdateDate = tokenUpdateDate;
    }

}
