package com.lending.lendingbackend.service.convertor;

import com.lending.lendingbackend.data.entity.Client;
import com.lending.lendingbackend.data.entity.Credit;
import com.lending.lendingbackend.data.entity.CreditApplication;
import com.lending.lendingbackend.data.entity.Manager;
import com.lending.lendingbackend.dto.CreditDTO;

public class CreditToCreditDTOConverter {
    public static CreditDTO convertCreditToCreditDTO(Credit credit) {
        CreditDTO creditDTO = new CreditDTO();
        creditDTO.setContractNumber(credit.getContractNumber());
        CreditApplication creditApplication = credit.getApprovedApplication();
        Client client = creditApplication.getClient();
        creditDTO.setClientName(getFullName(
               client.getLastName(),
               client.getFirstName(),
               client.getMiddleName()));
        creditDTO.setClientId(client.getAccountNumber());
        creditDTO.setClientPassportSeries(client.getPassportSeries());
        creditDTO.setClientPassportNumber(client.getPassportNumber());
        creditDTO.setClientBirthDate(client.getBirthDate());
        creditDTO.setClientAddress(client.getAddress());
        creditDTO.setClientPhone(client.getPhone());
        creditDTO.setClientEmail(client.getEmail());
        creditDTO.setCurrentAmount(credit.getCurrentAmount());

        creditDTO.setContractDate(credit.getContractDate());
        creditDTO.setInterestRate(creditApplication.getProduct().getInterestRate());
        creditDTO.setDownPayment(creditApplication.getDownPayment());
        creditDTO.setTerm(creditApplication.getTerm());
        creditDTO.setProductName(creditApplication.getProduct().getName());
        Manager manager = creditApplication.getManager();
        creditDTO.setManagerName(getFullName(
                manager.getLastName(),
                manager.getFirstName(),
                manager.getMiddleName()));
        creditDTO.setManagerPhone(manager.getPhone());
        creditDTO.setManagerEmail(manager.getEmail());
        creditDTO.setManagerId(manager.getEmployeeId());

        creditDTO.setRequestedAmount(creditApplication.getRequestedAmount());
        creditDTO.setStatus(credit.getStatus());
        creditDTO.setPaymentType(creditApplication.getPaymentType());
        creditDTO.setProductId(creditApplication.getProduct().getCode());

        return creditDTO;
    }

    private static String getFullName(String lastName, String firstName, String middleName) {
        return lastName + " " + firstName + " " + middleName;
    }
}
