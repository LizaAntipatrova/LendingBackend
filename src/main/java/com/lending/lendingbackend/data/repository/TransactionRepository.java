package com.lending.lendingbackend.data.repository;

import com.lending.lendingbackend.data.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    List<Transaction> findAllByCredit_ContractNumber(Long contactNumber);

    @Procedure(name = "create_monthly_payment")
    void createMonthlyPayment(
            @Param("p_contract_number") Long contractNumber,
            @Param("p_payment_amount") Double paymentAmount);

    @Procedure(name = "charge_penalty")
    void chargePenalty(
            @Param("p_contract_number") Long contractNumber);

    @Procedure(name = "charge_interest")
    void chargeInterest(
            @Param("p_contract_number") Long contractNumber);

    @Procedure(name = "early_repayment")
    void earlyRepayment(
            @Param("p_contract_number") Long contractNumber,
            @Param("p_repayment_amount") Double repaymentAmount);
}
