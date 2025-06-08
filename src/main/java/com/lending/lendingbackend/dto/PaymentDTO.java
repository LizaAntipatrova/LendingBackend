package com.lending.lendingbackend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaymentDTO {
    int paymentNumber;
    LocalDate recommendedDate;
    LocalDate criticalDate;
    BigDecimal principalPayment;
    BigDecimal interestPayment;
    BigDecimal totalPayment;
    BigDecimal accruedInterest;
    BigDecimal remainingAmount;
}
