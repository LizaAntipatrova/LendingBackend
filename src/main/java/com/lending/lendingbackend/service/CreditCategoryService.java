package com.lending.lendingbackend.service;

import com.lending.lendingbackend.data.entity.CreditCategory;
import com.lending.lendingbackend.data.repository.CreditProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreditCategoryService {
    private final CreditProductRepository creditProductRepository;
    
    public CreditCategory getCategoryByCreditProductCode(Long code) {
        return creditProductRepository.getCreditProductByCode(code).getCategory();
    }
}