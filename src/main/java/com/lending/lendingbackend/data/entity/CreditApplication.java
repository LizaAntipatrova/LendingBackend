package com.lending.lendingbackend.data.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@NamedStoredProcedureQueries({
        @NamedStoredProcedureQuery(
                name = "add_credit_application",
                procedureName = "add_credit_application",
                parameters = {
                        @StoredProcedureParameter(name = "p_client_id", type = Long.class, mode = ParameterMode.IN),
                        @StoredProcedureParameter(name = "p_product_id", type = Long.class, mode = ParameterMode.IN),
                        @StoredProcedureParameter(name = "p_manager_id", type = Long.class, mode = ParameterMode.IN),
                        @StoredProcedureParameter(name = "p_down_payment", type = BigDecimal.class, mode = ParameterMode.IN),
                        @StoredProcedureParameter(name = "p_requested_amount", type = BigDecimal.class, mode = ParameterMode.IN),
                        @StoredProcedureParameter(name = "p_term", type = Integer.class, mode = ParameterMode.IN),
                        @StoredProcedureParameter(name = "p_payment_type", type = String.class, mode = ParameterMode.IN),
                        @StoredProcedureParameter(name = "p_application_id", type = Long.class, mode = ParameterMode.OUT)
                }
        ),
        @NamedStoredProcedureQuery(
                name = "update_application_status",
                procedureName = "update_application_status",
                parameters = {
                        @StoredProcedureParameter(name = "p_application_id", type = Long.class, mode = ParameterMode.IN),
                        @StoredProcedureParameter(name = "p_status", type = String.class, mode = ParameterMode.IN)
                }
        )})

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
    private BigDecimal downPayment;
    private BigDecimal requestedAmount;
    private Integer term;

    @Enumerated(EnumType.STRING)
    private ApplicationStatus status;


    @Enumerated(EnumType.STRING)
    private PaymentType paymentType;
}