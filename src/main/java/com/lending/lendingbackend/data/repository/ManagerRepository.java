package com.lending.lendingbackend.data.repository;

import com.lending.lendingbackend.data.entity.Manager;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ManagerRepository extends JpaRepository<Manager, Long> {
    Manager findManagerByUser_Id(Long id);
}
