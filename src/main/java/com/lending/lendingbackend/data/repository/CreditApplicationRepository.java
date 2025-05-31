package com.lending.lendingbackend.data.repository;

import com.lending.lendingbackend.data.entity.CreditApplication;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CreditApplicationRepository extends JpaRepository<CreditApplication, Long> {
}
