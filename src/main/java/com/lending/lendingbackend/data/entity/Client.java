package com.lending.lendingbackend.data.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Check;

import java.time.LocalDate;
@NamedStoredProcedureQuery(
        name = "add_client",
        procedureName = "add_client",
        parameters = {
                @StoredProcedureParameter(name = "p_last_name", mode = ParameterMode.IN, type = String.class),
                @StoredProcedureParameter(name = "p_first_name", mode = ParameterMode.IN, type = String.class),
                @StoredProcedureParameter(name = "p_middle_name", mode = ParameterMode.IN, type = String.class),
                @StoredProcedureParameter(name = "p_passport_series", mode = ParameterMode.IN, type = String.class),
                @StoredProcedureParameter(name = "p_passport_number", mode = ParameterMode.IN, type = String.class),
                @StoredProcedureParameter(name = "p_birth_date", mode = ParameterMode.IN, type = LocalDate.class),
                @StoredProcedureParameter(name = "p_address", mode = ParameterMode.IN, type = String.class),
                @StoredProcedureParameter(name = "p_phone", mode = ParameterMode.IN, type = String.class),
                @StoredProcedureParameter(name = "p_email", mode = ParameterMode.IN, type = String.class),
                @StoredProcedureParameter(name = "p_login", mode = ParameterMode.IN, type = String.class),
                @StoredProcedureParameter(name = "p_password", mode = ParameterMode.IN, type = String.class),
                @StoredProcedureParameter(name = "p_account_number", mode = ParameterMode.OUT, type = String.class)
        }
)

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "clients")
@Check(constraints = "birth_date <= (now() - interval '18 years')")
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long accountNumber;
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