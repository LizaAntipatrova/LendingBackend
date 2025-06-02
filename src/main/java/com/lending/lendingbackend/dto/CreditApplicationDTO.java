package com.lending.lendingbackend.dto;

import com.lending.lendingbackend.data.entity.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class CreditApplicationDTO {

    private Long id;


    private String clientName;
    private String clientPassportSeries;
    private String clientPassportNumber;
    private LocalDate clientBirthDate;
    private String clientAddress;
    private String clientPhone;
    private String clientEmail;
    private Long clientId;

    private String productName;
    private BigDecimal interestRate;

    private Long productId;

    private String managerName;
    private String managerPhone;
    private String managerEmail;
    private Long managerId;



    
    private LocalDateTime applicationDate;
    private BigDecimal downPayment;
    private BigDecimal requestedAmount;
    private Integer term;
    private ApplicationStatus status;

    private PaymentType paymentType;
}