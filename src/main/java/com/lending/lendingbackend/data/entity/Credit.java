package com.lending.lendingbackend.data.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "credits")
public class Credit {
    @Id
    private String contractNumber;
    private LocalDate contractDate;
    
    @OneToOne
    @JoinColumn(name = "application_id")
    private CreditApplication approvedApplication;
    
    @Enumerated(EnumType.STRING)
    private CreditStatus status;
}