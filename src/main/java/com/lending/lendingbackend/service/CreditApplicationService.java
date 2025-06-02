package com.lending.lendingbackend.service;


import com.lending.lendingbackend.data.entity.CreditCategory;
import com.lending.lendingbackend.data.repository.CreditApplicationRepository;
import com.lending.lendingbackend.dto.CreditApplicationDTO;
import com.lending.lendingbackend.service.convertor.CreditApplicationToCreditApplicationDTOConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@Service
@RequiredArgsConstructor
public class CreditApplicationService {
    private final CreditApplicationRepository creditApplicationRepository;
    private final ClientService clientService;
    private final ManagerService managerService;
    private final CreditProductService creditProductService;
    private final CreditCategoryService creditCategoryService;

    public CreditApplicationDTO getCreditApplicationDTOByApplicationId(Long id){
        return CreditApplicationToCreditApplicationDTOConverter
                .convertCreditApplicationToCreditApplicationDTO(
                        creditApplicationRepository.findCreditApplicationById(id));
    }
    public void createCreditApplication(CreditApplicationDTO creditApplicationDTO){
        Long managerId;
        if(creditApplicationDTO.getManagerId() == null) {
            managerId = getRandomElement(managerService.getAllManagerBySpecialization(
                    creditCategoryService.getCategoryByCreditProductCode(creditApplicationDTO.getProductId()))).getEmployeeId();
            creditApplicationDTO.setClientId(clientService.findClientByUserId(creditApplicationDTO.getClientId()).getAccountNumber());
        }else {
            managerId = managerService.getManagerIdByUserId(creditApplicationDTO.getManagerId());
        }

        creditApplicationRepository.addCreditApplication(creditApplicationDTO.getClientId(),
                creditApplicationDTO.getProductId(),
                managerId,
                creditApplicationDTO.getDownPayment(),
                creditApplicationDTO.getRequestedAmount(),
                creditApplicationDTO.getTerm(),
                creditApplicationDTO.getPaymentType().toString());
    }
    private static <T> T getRandomElement(List<T> list) {
        int randomIndex = ThreadLocalRandom.current().nextInt(list.size());
        return list.get(randomIndex);
    }
}
