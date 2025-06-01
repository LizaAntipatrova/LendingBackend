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
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ManagerService {
    private final ManagerRepository managerRepository;
    private final CreditCategoryService creditCategoryService;

    public Manager save(Manager teacher) {
        return managerRepository.save(teacher);
    }

    private Manager findManagerByUserId(Long id) {
        return managerRepository.findManagerByUser_Id(id);
    }

    public boolean existManagerSpecialization(Long managerUserId, Long productCode){
        return managerRepository.existsByUser_IdAndSpecializationsContains(managerUserId, creditCategoryService.getCategoryByCreditProductCode(productCode));

    }

    public ManagerDTO getManagerDTOByUserId(Long userId) {
        return ManagerToManagerDTOConverter.convertManagerToManagerDTO(findManagerByUserId(userId));
    }
    public Long getManagerIdByUserId(Long userId){
        return findManagerByUserId(userId).getEmployeeId();
    }

    public List<Manager> getAllManagerBySpecialization(CreditCategory creditCategory) {
        return managerRepository.findAllBySpecializationsContains(creditCategory);
    }

    public Set<CreditCategory> getCreditCategoriesByManagerUserId(Long userId){
        return findManagerByUserId(userId).getSpecializations();
    }

    public List<CreditApplicationDTO> getManagersApplicationListDTOByUserId(Long userId){
        return findManagerByUserId(userId).getCreditApplications().stream()
                .map(CreditApplicationToCreditApplicationDTOConverter::convertCreditApplicationToCreditApplicationDTO)
                .collect(Collectors.toList());
    }



}
