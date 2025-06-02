package com.lending.lendingbackend.data.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@NamedStoredProcedureQueries({
        @NamedStoredProcedureQuery(
                name = "Credit.addCredit",
                procedureName = "add_credit",
                parameters = {
                        @StoredProcedureParameter(name = "p_application_id", type = Long.class, mode = ParameterMode.IN),
                        @StoredProcedureParameter(name = "p_status", type = String.class, mode = ParameterMode.IN)
                }
        ),
        @NamedStoredProcedureQuery(
                name = "Credit.updateCreditStatus",
                procedureName = "update_credit_status",
                parameters = {
                        @StoredProcedureParameter(name = "p_contract_number", type = Long.class, mode = ParameterMode.IN),
                        @StoredProcedureParameter(name = "p_status", type = String.class, mode = ParameterMode.IN)
                }
        )
})

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "credits")
public class Credit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long contractNumber;
    private LocalDate contractDate;
    
    @OneToOne
    @JoinColumn(name = "application_id")
    private CreditApplication approvedApplication;
    
    @Enumerated(EnumType.STRING)
    private CreditStatus status;
}