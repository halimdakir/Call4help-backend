package com.solidbeans.call4help.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.ZonedDateTime;

@Data
@NoArgsConstructor
@Entity
public class Profile {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    @Column(name="anonymous")
    private boolean isAnonymous;
    @Column(name="full_name")
    private String fullName;
    private String email;
    @Column(name="phone_number")
    private String phoneNumber;
    @Column(name="profile_update_date")
    private ZonedDateTime updateDate;




    @JsonBackReference
    @OneToOne(fetch = FetchType.LAZY, cascade =  CascadeType.ALL, mappedBy = "profile")
    private Endpoint endpoint;

    @JsonBackReference
    @OneToOne(fetch = FetchType.LAZY, cascade =  CascadeType.ALL, mappedBy = "profile")
    private Position position;

    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @JsonManagedReference
    @OneToOne(fetch = FetchType.LAZY, optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", nullable = false)
    private Users users;

    public Profile(boolean isAnonymous, String fullName, String email, String phoneNumber, ZonedDateTime updateDate, Users users) {
        this.isAnonymous = isAnonymous;
        this.fullName = fullName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.updateDate = updateDate;
        this.users = users;
    }
}
