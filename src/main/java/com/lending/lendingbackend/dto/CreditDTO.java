package com.lending.lendingbackend.dto;

import com.lending.lendingbackend.data.entity.CreditApplication;
import com.lending.lendingbackend.data.entity.CreditStatus;
import com.lending.lendingbackend.data.entity.PaymentType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class CreditDTO {

    private String contractNumber;
    private LocalDate contractDate;
    private String clientName;


    private String productName;

    private String managerName;

    private LocalDateTime applicationDate;
    private BigDecimal interestRate;
    private BigDecimal downPayment;
    private BigDecimal requestedAmount;
    private Integer term;

    private PaymentType paymentType;
    private CreditStatus status;
}