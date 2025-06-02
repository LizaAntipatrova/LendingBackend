package com.lending.lendingbackend.data.repository;

import com.lending.lendingbackend.data.entity.CreditApplication;
import com.lending.lendingbackend.data.entity.PaymentType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;

public interface CreditApplicationRepository extends JpaRepository<CreditApplication, Long> {
    CreditApplication findCreditApplicationById(Long id);

    @Procedure(name = "add_credit_application")
    Long addCreditApplication(
            @Param("p_client_id") Long clientId,
            @Param("p_product_id") Long productId,
            @Param("p_manager_id") Long managerId,
            @Param("p_down_payment") BigDecimal downPayment,
            @Param("p_requested_amount") BigDecimal requestedAmount,
            @Param("p_term") Integer term,
            @Param("p_payment_type") String paymentType);
}
