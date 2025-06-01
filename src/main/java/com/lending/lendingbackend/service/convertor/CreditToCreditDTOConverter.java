package com.lending.lendingbackend.service.convertor;

import com.lending.lendingbackend.data.entity.Credit;
import com.lending.lendingbackend.data.entity.CreditApplication;
import com.lending.lendingbackend.dto.CreditDTO;

public class CreditToCreditDTOConverter {
    public static CreditDTO convertCreditToCreditDTO(Credit credit) {
        CreditDTO creditDTO = new CreditDTO();
        creditDTO.setContractNumber(credit.getContractNumber());
        CreditApplication creditApplication = credit.getApprovedApplication();
        creditDTO.setClientName(getFullName(
                creditApplication.getClient().getLastName(),
                creditApplication.getClient().getFirstName(),
                creditApplication.getClient().getMiddleName()));
        creditDTO.setApplicationDate(creditApplication.getApplicationDate());
        creditDTO.setInterestRate(creditApplication.getProduct().getInterestRate());
        creditDTO.setDownPayment(creditApplication.getDownPayment());
        creditDTO.setTerm(creditApplication.getTerm());
        creditDTO.setProductName(creditApplication.getProduct().getName());
        creditDTO.setManagerName(getFullName(
                creditApplication.getManager().getLastName(),
                creditApplication.getManager().getFirstName(),
                creditApplication.getManager().getMiddleName()));
        creditDTO.setRequestedAmount(creditApplication.getRequestedAmount());
        creditDTO.setStatus(credit.getStatus());
        creditDTO.setPaymentType(creditApplication.getPaymentType());

        return creditDTO;
    }

    private static String getFullName(String lastName, String firstName, String middleName) {
        return lastName + " " + firstName + " " + middleName;
    }
}
