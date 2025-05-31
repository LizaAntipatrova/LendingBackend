package com.lending.lendingbackend.data.repository;

import com.lending.lendingbackend.data.entity.CreditCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CreditCategoryRepository extends JpaRepository<CreditCategory, Long> {

}
