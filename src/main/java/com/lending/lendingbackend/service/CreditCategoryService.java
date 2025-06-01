package com.lending.lendingbackend.service;

import com.lending.lendingbackend.data.entity.CreditCategory;
import com.lending.lendingbackend.data.repository.CreditCategoryRepository;
import com.lending.lendingbackend.data.repository.CreditProductRepository;
import com.lending.lendingbackend.dto.CreditCategoryDTO;
import com.lending.lendingbackend.service.convertor.CreditCategoryToCreditCategoryDTOConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CreditCategoryService {
    private final CreditProductRepository creditProductRepository;

    public CreditCategory getCategoryByCreditProductCode(Long code) {
        return creditProductRepository.getCreditProductByCode(code).getCategory();
    }

}