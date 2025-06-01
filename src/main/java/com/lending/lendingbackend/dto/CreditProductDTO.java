package com.lending.lendingbackend.dto;

import com.lending.lendingbackend.data.entity.CreditCategory;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreditProductDTO {
    private Long code;
    private String name;
    private BigDecimal interestRate;
    private BigDecimal minAmount;
    private BigDecimal maxAmount;
    private Integer minTerm;
    private Integer maxTerm;
    private BigDecimal minDownPayment;
    private String description;
    private String category;
    private Long categoryId;
}
