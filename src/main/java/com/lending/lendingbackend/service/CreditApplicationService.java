package com.lending.lendingbackend.service;


import com.lending.lendingbackend.data.repository.CreditProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreditApplicationService {
    private final CreditProductRepository creditProductRepository;
}
