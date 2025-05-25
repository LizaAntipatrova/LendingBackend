package com.lending.lendingbackend.service;


import com.lending.lendingbackend.data.entity.Manager;
import com.lending.lendingbackend.data.repository.ManagerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ManagerService {
    private final ManagerRepository teacherRepository;

    public Manager save(Manager teacher) {
        return teacherRepository.save(teacher);
    }

    public Manager findManagerByUserId(Long id) {
        return teacherRepository.findManagerByUser_Id(id);
    }


}
