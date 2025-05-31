package com.lending.lendingbackend.dto;

import com.lending.lendingbackend.data.entity.*;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class CreditApplicationDTO {

    private Long id;


    private String clientName;


    private String productName;

    private String managerName;
    private Double interestRate;
    
    private LocalDateTime applicationDate;
    private Double downPayment;
    private Double requestedAmount;
    private Integer term;

    private ApplicationStatus status;


    private PaymentType paymentType;
}