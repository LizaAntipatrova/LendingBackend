package com.lending.lendingbackend.dto;

import com.lending.lendingbackend.data.entity.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Check;

import java.time.LocalDate;



@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClientDTO {

    private Long accountNumber;
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