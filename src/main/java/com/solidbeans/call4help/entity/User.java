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
public class User {

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

}
