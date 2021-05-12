package com.solidbeans.call4help.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProfileDTO {
    private boolean anonymous;
    private String fullName;
    private String email;
    private String phoneNumber;
    private String address;
    private String postCode;
    private String ort;
}
