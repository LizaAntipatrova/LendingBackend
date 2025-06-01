package com.lending.lendingbackend.dto;

import com.lending.lendingbackend.data.entity.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
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


    private BigDecimal interestRate;
    
    private LocalDateTime applicationDate;
    private BigDecimal downPayment;
    private BigDecimal requestedAmount;
    private Integer term;

    private ApplicationStatus status;


    private PaymentType paymentType;
}