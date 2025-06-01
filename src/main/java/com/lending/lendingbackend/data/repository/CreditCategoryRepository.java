package com.lending.lendingbackend.data.repository;

import com.lending.lendingbackend.data.entity.CreditCategory;
import com.lending.lendingbackend.data.entity.Manager;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CreditCategoryRepository extends JpaRepository<CreditCategory, Long> {
    List<CreditCategory> findCreditCategoriesByManagersContains(Manager manager);

}
