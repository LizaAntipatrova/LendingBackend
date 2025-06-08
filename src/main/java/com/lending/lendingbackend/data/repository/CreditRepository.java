package com.lending.lendingbackend.data.repository;

import com.lending.lendingbackend.data.entity.Credit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CreditRepository extends JpaRepository<Credit, Long> {
    List<Credit> findByApprovedApplication_Client_User_Id(Long clientUserId);
    List<Credit> findByApprovedApplication_Manager_User_Id(Long managerId);
    Credit findCreditByContractNumber(Long contractNumber);

    @Procedure(name = "Credit.addCredit")
    void addCredit(@Param("p_application_id") Long applicationId,
                   @Param("p_status") String status);

    @Procedure(name = "Credit.updateCreditStatus")
    void updateCreditStatus(@Param("p_contract_number") Long contractNumber,
                            @Param("p_status") String status);

    @Procedure(name = "Credit.confirmCredit")
    void confirmCredit(@Param("p_contract_number") Long contractNumber);
}
