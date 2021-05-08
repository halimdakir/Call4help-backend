package com.solidbeans.call4help.dtos;

import lombok.Data;

@Data
public class ProfileDTO {
    private boolean anonymous;
    private String fullName;
    private String email;
    private String phoneNumber;
    private String address;
    private String postCode;
    private String ort;
}
