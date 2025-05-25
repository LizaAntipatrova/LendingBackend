package com.lending.lendingbackend.data.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "applications")
public class CreditApplication {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;
    
    @ManyToOne
    @JoinColumn(name = "product_id")
    private CreditProduct product;
    
    @ManyToOne
    @JoinColumn(name = "manager_id")
    private Manager manager;
    
    private LocalDateTime applicationDate;
    private Double downPayment;
    private Double requestedAmount;
    private Integer term;

    @Enumerated(EnumType.STRING)
    private ApplicationStatus status;
    
    private String decision;

    @Enumerated(EnumType.STRING)
    private PaymentType paymentType;
}