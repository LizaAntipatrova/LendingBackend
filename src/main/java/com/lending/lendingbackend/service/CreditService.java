package com.lending.lendingbackend.service;

import com.lending.lendingbackend.data.repository.CreditRepository;
import com.lending.lendingbackend.dto.CreditDTO;
import com.lending.lendingbackend.service.convertor.CreditToCreditDTOConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CreditService {
    private final CreditRepository creditRepository;

    public List<CreditDTO> getClientsCreditListDTOByUserId(Long userId){
        return creditRepository.findByApprovedApplication_Client_User_Id(userId).stream()
                .map(CreditToCreditDTOConverter::convertCreditToCreditDTO)
                .collect(Collectors.toList());
    }

}
