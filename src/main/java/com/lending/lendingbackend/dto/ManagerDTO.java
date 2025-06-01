package com.lending.lendingbackend.dto;

import com.lending.lendingbackend.data.entity.CreditApplication;
import com.lending.lendingbackend.data.entity.CreditCategory;
import com.lending.lendingbackend.data.entity.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ManagerDTO {

    private Long employeeId;
    private String lastName;
    private String firstName;
    private String middleName;
    private String phone;
    private String email;

}