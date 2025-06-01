package com.lending.lendingbackend.service;

import com.lending.lendingbackend.data.entity.CreditCategory;
import com.lending.lendingbackend.data.repository.CreditProductRepository;
import com.lending.lendingbackend.dto.CreditProductDTO;
import com.lending.lendingbackend.service.convertor.CreditProductToCreditProductDTOConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CreditProductService {
    private final CreditProductRepository creditProductRepository;

    public List<CreditProductDTO> getAllCreditProducts(){
        return creditProductRepository.findAll().stream()
                .map(CreditProductToCreditProductDTOConverter::convertCreditProductToCreditProductDTO)
                .collect(Collectors.toList());
    }
    public CreditCategory getCategoryByCreditProductCode(Long code){
        return creditProductRepository.getCreditProductByCode(code).getCategory();
    }

}
