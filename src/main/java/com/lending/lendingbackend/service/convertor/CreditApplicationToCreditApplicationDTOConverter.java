package com.lending.lendingbackend.service.convertor;

import com.lending.lendingbackend.data.entity.CreditApplication;
import com.lending.lendingbackend.dto.CreditApplicationDTO;

public class CreditApplicationToCreditApplicationDTOConverter {
    public static CreditApplicationDTO convertCreditApplicationToCreditApplicationDTO(CreditApplication creditApplication) {
        CreditApplicationDTO creditApplicationDTO = new CreditApplicationDTO();
        creditApplicationDTO.setId(creditApplication.getId());
        creditApplicationDTO.setClientName(getFullName(
                creditApplication.getClient().getLastName(),
                creditApplication.getClient().getFirstName(),
                creditApplication.getClient().getMiddleName()));
        creditApplicationDTO.setApplicationDate(creditApplication.getApplicationDate());
        creditApplicationDTO.setInterestRate(creditApplication.getProduct().getInterestRate());
        creditApplicationDTO.setDownPayment(creditApplication.getDownPayment());
        creditApplicationDTO.setTerm(creditApplication.getTerm());
        creditApplicationDTO.setProductName(creditApplication.getProduct().getName());
        creditApplicationDTO.setManagerName(getFullName(
                creditApplication.getManager().getLastName(),
                creditApplication.getManager().getFirstName(),
                creditApplication.getManager().getMiddleName()));
        creditApplicationDTO.setRequestedAmount(creditApplication.getRequestedAmount());
        creditApplicationDTO.setStatus(creditApplication.getStatus());
        creditApplicationDTO.setPaymentType(creditApplication.getPaymentType());

        return creditApplicationDTO;
    }
    private static String getFullName(String lastName, String firstName, String middleName) {
        return lastName + " " + firstName + " " + middleName;
    }
}
