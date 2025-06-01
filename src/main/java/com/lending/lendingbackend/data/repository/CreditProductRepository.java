package com.lending.lendingbackend.data.repository;

import com.lending.lendingbackend.data.entity.CreditProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CreditProductRepository extends JpaRepository<CreditProduct, Long> {
    CreditProduct getCreditProductByCode(Long code);
}
