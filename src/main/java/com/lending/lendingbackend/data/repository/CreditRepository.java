package com.lending.lendingbackend.data.repository;

import com.lending.lendingbackend.data.entity.Credit;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CreditRepository extends JpaRepository<Credit, Long> {
    List<Credit> findByApprovedApplication_Client_User_Id(Long clientUserId);
}
