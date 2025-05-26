package com.lending.lendingbackend.dto;

import com.lending.lendingbackend.data.entity.CreditCategory;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreditProductDTO {
    private Long code;
    private String name;
    private Double interestRate;
    private Double minAmount;
    private Double maxAmount;
    private Integer minTerm;
    private Integer maxTerm;
    private Double minDownPayment;
    private String description;
    private String category;
}
