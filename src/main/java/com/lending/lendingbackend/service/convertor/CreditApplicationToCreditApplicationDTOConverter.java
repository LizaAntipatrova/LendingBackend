package com.lending.lendingbackend.service.convertor;

import com.lending.lendingbackend.data.entity.Client;
import com.lending.lendingbackend.data.entity.CreditApplication;
import com.lending.lendingbackend.data.entity.Manager;
import com.lending.lendingbackend.dto.CreditApplicationDTO;

public class CreditApplicationToCreditApplicationDTOConverter {
    public static CreditApplicationDTO convertCreditApplicationToCreditApplicationDTO(CreditApplication creditApplication) {
        CreditApplicationDTO creditApplicationDTO = new CreditApplicationDTO();
        creditApplicationDTO.setId(creditApplication.getId());
        Client client = creditApplication.getClient();
        creditApplicationDTO.setClientName(getFullName(
                client.getLastName(),
                client.getFirstName(),
                client.getMiddleName()));
        creditApplicationDTO.setClientPassportSeries(client.getPassportSeries());
        creditApplicationDTO.setClientPassportNumber(client.getPassportNumber());
        creditApplicationDTO.setClientAddress(client.getAddress());
        creditApplicationDTO.setClientPhone(client.getPhone());
        creditApplicationDTO.setClientEmail(client.getEmail());
        creditApplicationDTO.setClientId(client.getAccountNumber());
        creditApplicationDTO.setApplicationDate(creditApplication.getApplicationDate());

        creditApplicationDTO.setInterestRate(creditApplication.getProduct().getInterestRate());
        creditApplicationDTO.setDownPayment(creditApplication.getDownPayment());
        creditApplicationDTO.setTerm(creditApplication.getTerm());
        creditApplicationDTO.setProductName(creditApplication.getProduct().getName());

        Manager manager = creditApplication.getManager();

        creditApplicationDTO.setManagerName(getFullName(
                manager.getLastName(),
                manager.getFirstName(),
                manager.getMiddleName()));
        creditApplicationDTO.setManagerPhone(manager.getPhone());
        creditApplicationDTO.setManagerEmail(manager.getEmail());
        creditApplicationDTO.setManagerId(manager.getEmployeeId());
        creditApplicationDTO.setRequestedAmount(creditApplication.getRequestedAmount());
        creditApplicationDTO.setStatus(creditApplication.getStatus());
        creditApplicationDTO.setPaymentType(creditApplication.getPaymentType());

        return creditApplicationDTO;
    }
    private static String getFullName(String lastName, String firstName, String middleName) {
        return lastName + " " + firstName + " " + middleName;
    }
}
