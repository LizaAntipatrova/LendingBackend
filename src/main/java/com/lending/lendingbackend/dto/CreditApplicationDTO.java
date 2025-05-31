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
    private Long clientId;

    private String productName;
    private Long productId;

    private String managerName;
    private Long managerId;


    private Double interestRate;
    
    private LocalDateTime applicationDate;
    private Double downPayment;
    private Double requestedAmount;
    private Integer term;

    private ApplicationStatus status;


    private PaymentType paymentType;
}