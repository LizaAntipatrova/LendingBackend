package com.lending.lendingbackend.service.convertor;

import com.lending.lendingbackend.data.entity.CreditProduct;
import com.lending.lendingbackend.dto.CreditProductDTO;

public class CreditProductToCreditProductDTOConverter {
    public static CreditProductDTO convertCreditProductToCreditProductDTO(CreditProduct creditProduct) {
        CreditProductDTO creditProductDTO = new CreditProductDTO();
        creditProductDTO.setCode(creditProduct.getCode());
        creditProductDTO.setName(creditProduct.getName());
        creditProductDTO.setInterestRate(creditProduct.getInterestRate());
        creditProductDTO.setMinAmount(creditProduct.getMinAmount());
        creditProductDTO.setMaxAmount(creditProduct.getMaxAmount());
        creditProductDTO.setMinTerm(creditProduct.getMinTerm());
        creditProductDTO.setMinDownPayment(creditProduct.getMinDownPayment());
        creditProductDTO.setDescription(creditProduct.getDescription());
        creditProductDTO.setCategory(creditProduct.getCategory().getText_name());

        return creditProductDTO;
    }

}
