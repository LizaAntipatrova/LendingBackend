package com.lending.lendingbackend.data.entity;

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
@Entity
@Table(name = "clients")
@Check(constraints = "birth_date <= (now() - interval '18 years')")
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String accountNumber;
    @NotBlank
    private String lastName;
    @NotBlank
    private String firstName;
    private String middleName;
    @NotBlank
    private String passportSeries;
    @NotBlank
    private String passportNumber;
    @NotBlank
    private LocalDate birthDate;
    private String address;
    @NotBlank
    private String phone;
    private String email;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

}