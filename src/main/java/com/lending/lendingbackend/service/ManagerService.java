package com.lending.lendingbackend.service;


import com.lending.lendingbackend.data.entity.CreditCategory;
import com.lending.lendingbackend.data.entity.Manager;
import com.lending.lendingbackend.data.repository.ManagerRepository;
import com.lending.lendingbackend.dto.CreditApplicationDTO;
import com.lending.lendingbackend.dto.ManagerDTO;
import com.lending.lendingbackend.service.convertor.CreditApplicationToCreditApplicationDTOConverter;
import com.lending.lendingbackend.service.convertor.ManagerToManagerDTOConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ManagerService {
    private final ManagerRepository managerRepository;

    public Manager save(Manager teacher) {
        return managerRepository.save(teacher);
    }

    private Manager findManagerByUserId(Long id) {
        return managerRepository.findManagerByUser_Id(id);
    }

    public ManagerDTO getManagerDTOByUserId(Long userId) {
        return ManagerToManagerDTOConverter.convertManagerToManagerDTO(findManagerByUserId(userId));
    }

    public List<Manager> getAllManagerBySpecialization(CreditCategory creditCategory) {
        return managerRepository.findAllBySpecializationsContains(creditCategory);
    }

    public List<CreditApplicationDTO> getManagersApplicationListDTOByUserId(Long userId){
        return findManagerByUserId(userId).getCreditApplications().stream()
                .map(CreditApplicationToCreditApplicationDTOConverter::convertCreditApplicationToCreditApplicationDTO)
                .collect(Collectors.toList());
    }



}
