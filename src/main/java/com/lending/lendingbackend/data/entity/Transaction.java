package com.lending.lendingbackend.data.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@NamedStoredProcedureQueries({
        @NamedStoredProcedureQuery(
                name = "create_monthly_payment",
                procedureName = "create_monthly_payment",
                parameters = {
                        @StoredProcedureParameter(name = "p_contract_number", type = Long.class, mode = ParameterMode.IN),
                        @StoredProcedureParameter(name = "p_payment_amount", type = Double.class, mode = ParameterMode.IN)
                }
        ),
        @NamedStoredProcedureQuery(
                name = "charge_penalty",
                procedureName = "charge_penalty",
                parameters = {
                        @StoredProcedureParameter(name = "p_contract_number", type = Long.class, mode = ParameterMode.IN)
                }
        ),
        @NamedStoredProcedureQuery(
                name = "charge_interest",
                procedureName = "charge_interest",
                parameters = {
                        @StoredProcedureParameter(name = "p_contract_number", type = Long.class, mode = ParameterMode.IN)
                }
        ),
        @NamedStoredProcedureQuery(
                name = "early_repayment",
                procedureName = "early_repayment",
                parameters = {
                        @StoredProcedureParameter(name = "p_contract_number", type = Long.class, mode = ParameterMode.IN),
                        @StoredProcedureParameter(name = "p_repayment_amount", type = Double.class, mode = ParameterMode.IN)
                }
        )
})
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "transactions")
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime transactionDate;
    
    @ManyToOne
    @JoinColumn(name = "credit_id")
    private Credit credit;
    
    private BigDecimal amount;

    @Enumerated(EnumType.STRING)
    private TransactionType description;
}