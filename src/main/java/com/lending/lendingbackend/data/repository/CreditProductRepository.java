package com.lending.lendingbackend.data.repository;

import com.lending.lendingbackend.data.entity.CreditApplication;
import com.lending.lendingbackend.data.entity.CreditCategory;
import com.lending.lendingbackend.data.entity.CreditProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;


@Repository
public interface CreditProductRepository extends JpaRepository<CreditProduct, Long> {
    CreditProduct getCreditProductByCode(Long code);
    List<CreditProduct> getCreditProductsByCategoryIn(Set<CreditCategory> creditCategories);

    @Procedure(procedureName = "add_credit_product")
    void addCreditProduct(
            @Param("p_name") String name,
            @Param("p_interest_rate") BigDecimal interestRate,
            @Param("p_min_amount") BigDecimal minAmount,
            @Param("p_max_amount") BigDecimal maxAmount,
            @Param("p_min_term") Integer minTerm,
            @Param("p_max_term") Integer maxTerm,
            @Param("p_min_down_payment") BigDecimal minDownPayment,
            @Param("p_description") String description,
            @Param("p_category_id") Long categoryId
    );
    @Procedure(procedureName = "update_credit_product")
    void updateCreditProduct(
            @Param("p_code") Long code,
            @Param("p_interest_rate") BigDecimal interestRate,
            @Param("p_min_amount") BigDecimal minAmount,
            @Param("p_max_amount") BigDecimal maxAmount,
            @Param("p_min_term") Integer minTerm,
            @Param("p_max_term") Integer maxTerm,
            @Param("p_min_down_payment") BigDecimal minDownPayment,
            @Param("p_description") String description
    );

    @Procedure(procedureName = "delete_credit_product")
    void deleteCreditProduct(@Param("p_code") Long code);
}
