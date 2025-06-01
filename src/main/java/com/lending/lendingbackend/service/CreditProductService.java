package com.lending.lendingbackend.service;

import com.lending.lendingbackend.data.entity.CreditCategory;
import com.lending.lendingbackend.data.entity.CreditProduct;
import com.lending.lendingbackend.data.repository.CreditProductRepository;
import com.lending.lendingbackend.dto.CreditDTO;
import com.lending.lendingbackend.dto.CreditProductDTO;
import com.lending.lendingbackend.service.convertor.CreditProductToCreditProductDTOConverter;
import com.lending.lendingbackend.service.convertor.CreditToCreditDTOConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CreditProductService {
    private final CreditProductRepository creditProductRepository;
    private final ManagerService managerService;

    public List<CreditProductDTO> getAllCreditProducts(){
        return creditProductRepository.findAll().stream()
                .map(CreditProductToCreditProductDTOConverter::convertCreditProductToCreditProductDTO)
                .collect(Collectors.toList());
    }
    private CreditProduct getCreditProductByCode(Long code){
        return creditProductRepository.getCreditProductByCode(code);
    }

    public CreditProductDTO getCreditProductDTOByCreditProductCode(Long code){
        return CreditProductToCreditProductDTOConverter.convertCreditProductToCreditProductDTO(getCreditProductByCode(code));
    }

    public List<CreditProductDTO> getCreditProductsByManagerUserId(Long userId){
        return creditProductRepository.getCreditProductsByCategoryIn
                (managerService.getCreditCategoriesByManagerUserId(userId)).stream()
                .map(CreditProductToCreditProductDTOConverter::convertCreditProductToCreditProductDTO)
                .collect(Collectors.toList());
    }


}
