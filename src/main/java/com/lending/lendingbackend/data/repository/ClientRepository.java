package com.lending.lendingbackend.data.repository;

import com.lending.lendingbackend.data.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
    Client findClientByUser_Id(Long Id);

    @Procedure(name = "add_client")
    String addClient(
            @Param("p_last_name") String lastName,
            @Param("p_first_name") String firstName,
            @Param("p_middle_name") String middleName,
            @Param("p_passport_series") String passportSeries,
            @Param("p_passport_number") String passportNumber,
            @Param("p_birth_date") LocalDate birthDate,
            @Param("p_address") String address,
            @Param("p_phone") String phone,
            @Param("p_email") String email,
            @Param("p_login") String login,
            @Param("p_password") String password
    );
}