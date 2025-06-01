package com.lending.lendingbackend.data.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
@NamedStoredProcedureQueries({
        @NamedStoredProcedureQuery(
                name = "CreditProduct.addCreditProduct",
                procedureName = "add_credit_product",
                parameters = {
                        @StoredProcedureParameter(name = "p_name", type = String.class, mode = ParameterMode.IN),
                        @StoredProcedureParameter(name = "p_interest_rate", type = BigDecimal.class, mode = ParameterMode.IN),
                        @StoredProcedureParameter(name = "p_min_amount", type = BigDecimal.class, mode = ParameterMode.IN),
                        @StoredProcedureParameter(name = "p_max_amount", type = BigDecimal.class, mode = ParameterMode.IN),
                        @StoredProcedureParameter(name = "p_min_term", type = Integer.class, mode = ParameterMode.IN),
                        @StoredProcedureParameter(name = "p_max_term", type = Integer.class, mode = ParameterMode.IN),
                        @StoredProcedureParameter(name = "p_min_down_payment", type = BigDecimal.class, mode = ParameterMode.IN),
                        @StoredProcedureParameter(name = "p_description", type = String.class, mode = ParameterMode.IN),
                        @StoredProcedureParameter(name = "p_category_id", type = Long.class, mode = ParameterMode.IN)
                }
        ),
        @NamedStoredProcedureQuery(
                name = "CreditProduct.updateCreditProduct",
                procedureName = "update_credit_product",
                parameters = {
                        @StoredProcedureParameter(name = "p_code", type = Long.class, mode = ParameterMode.IN),
                        @StoredProcedureParameter(name = "p_interest_rate", type = BigDecimal.class, mode = ParameterMode.IN),
                        @StoredProcedureParameter(name = "p_min_amount", type = BigDecimal.class, mode = ParameterMode.IN),
                        @StoredProcedureParameter(name = "p_max_amount", type = BigDecimal.class, mode = ParameterMode.IN),
                        @StoredProcedureParameter(name = "p_min_term", type = Integer.class, mode = ParameterMode.IN),
                        @StoredProcedureParameter(name = "p_max_term", type = Integer.class, mode = ParameterMode.IN),
                        @StoredProcedureParameter(name = "p_min_down_payment", type = BigDecimal.class, mode = ParameterMode.IN),
                        @StoredProcedureParameter(name = "p_description", type = String.class, mode = ParameterMode.IN)
                }
        ),
        @NamedStoredProcedureQuery(
                name = "CreditProduct.deleteCreditProduct",
                procedureName = "delete_credit_product",
                parameters = {
                        @StoredProcedureParameter(name = "p_code", type = Long.class, mode = ParameterMode.IN)
                }
        )
})

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
    private BigDecimal interestRate;
    private BigDecimal minAmount;
    private BigDecimal maxAmount;
    private Integer minTerm;
    private Integer maxTerm;
    private BigDecimal minDownPayment;
    private String description;
    
    @ManyToOne
    @JoinColumn(name = "category_id")
    private CreditCategory category;
}