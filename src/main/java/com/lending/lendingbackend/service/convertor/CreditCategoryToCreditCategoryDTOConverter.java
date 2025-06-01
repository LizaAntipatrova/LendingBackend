package com.lending.lendingbackend.service.convertor;

import com.lending.lendingbackend.data.entity.CreditCategory;
import com.lending.lendingbackend.dto.CreditCategoryDTO;

public class CreditCategoryToCreditCategoryDTOConverter {
    public static CreditCategoryDTO convertCreditCategoryToCreditCategoryDTO(CreditCategory creditCategory) {
        CreditCategoryDTO creditCategoryDTO = new CreditCategoryDTO();
        creditCategoryDTO.setId(creditCategory.getId());
        creditCategoryDTO.setText_name(creditCategory.getText_name());
        return creditCategoryDTO;
    }

}
