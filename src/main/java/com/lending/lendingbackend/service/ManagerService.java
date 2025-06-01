package com.lending.lendingbackend.service;


import com.lending.lendingbackend.data.entity.CreditCategory;
import com.lending.lendingbackend.data.entity.Manager;
import com.lending.lendingbackend.data.repository.ManagerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ManagerService {
    private final ManagerRepository managerRepository;

    public Manager save(Manager teacher) {
        return managerRepository.save(teacher);
    }

    public Manager findManagerByUserId(Long id) {
        return managerRepository.findManagerByUser_Id(id);
    }

    public List<Manager> getAllManagerBySpecialization(CreditCategory creditCategory){
        return managerRepository.findAllBySpecializationsContains(creditCategory);
    }


}
