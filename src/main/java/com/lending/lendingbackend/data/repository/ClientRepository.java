package com.lending.lendingbackend.data.repository;

import com.lending.lendingbackend.data.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
    Client findClientByUser_Id(Long Id);
}