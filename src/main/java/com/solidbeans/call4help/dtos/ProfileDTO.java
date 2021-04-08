package com.solidbeans.call4help.dtos;

import lombok.Data;

@Data
public class ProfileDTO {
    private boolean isAnonymous;
    private String fullName;
    private String email;
    private String phoneNumber;
}
