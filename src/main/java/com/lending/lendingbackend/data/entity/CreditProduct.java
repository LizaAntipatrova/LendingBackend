package com.lending.lendingbackend.data.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "credit_products")
public class CreditProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long code;
    private String name;
    private Double interestRate;
    private Double minAmount;
    private Double maxAmount;
    private Integer minTerm;
    private Integer maxTerm;
    private Double minDownPayment;
    private String description;
    
    @ManyToOne
    @JoinColumn(name = "category_id")
    private CreditCategory category;
}