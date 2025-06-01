package com.lending.lendingbackend.data.repository;

import com.lending.lendingbackend.data.entity.CreditApplication;
import com.lending.lendingbackend.data.entity.CreditCategory;
import com.lending.lendingbackend.data.entity.CreditProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;


@Repository
public interface CreditProductRepository extends JpaRepository<CreditProduct, Long> {
    CreditProduct getCreditProductByCode(Long code);
    List<CreditProduct> getCreditProductsByCategoryIn(Set<CreditCategory> creditCategories);
}
