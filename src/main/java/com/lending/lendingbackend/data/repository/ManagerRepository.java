package com.lending.lendingbackend.data.repository;

import com.lending.lendingbackend.data.entity.CreditCategory;
import com.lending.lendingbackend.data.entity.Manager;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface ManagerRepository extends JpaRepository<Manager, Long> {
    Manager findManagerByUser_Id(Long id);
    List<Manager> findAllBySpecializationsContains(CreditCategory creditCategory);
    boolean existsByUser_IdAndSpecializationsContains(Long userId, CreditCategory creditCategory);
}
