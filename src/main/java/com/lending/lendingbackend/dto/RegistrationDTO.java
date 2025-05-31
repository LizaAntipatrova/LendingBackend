package com.lending.lendingbackend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegistrationDTO {
    private String login;
    private String password;
    private String accountNumber;
    private String lastName;
    private String firstName;
    private String middleName;
    private String passportSeries;
    private String passportNumber;
    private LocalDate birthDate;
    private String address;
    private String phone;
    private String email;

}
