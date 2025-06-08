package com.lending.lendingbackend.service;

import com.lending.lendingbackend.data.entity.Credit;
import com.lending.lendingbackend.data.entity.CreditStatus;
import com.lending.lendingbackend.data.entity.Transaction;
import com.lending.lendingbackend.data.entity.TransactionType;
import com.lending.lendingbackend.data.repository.CreditRepository;
import com.lending.lendingbackend.dto.CreditDTO;
import com.lending.lendingbackend.service.convertor.CreditToCreditDTOConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
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
    public List<Credit> getAllCredits(){
        return creditRepository.findAll();
    }

    public List<CreditDTO> getManagersCreditListDTOByUserId(Long userId){
        return creditRepository.findByApprovedApplication_Manager_User_Id(userId).stream()
                .map(CreditToCreditDTOConverter::convertCreditToCreditDTO)
                .collect(Collectors.toList());
    }

    public CreditDTO getCreditByContractNumber(Long contractNumber){
        return CreditToCreditDTOConverter.convertCreditToCreditDTO(creditRepository.findCreditByContractNumber(contractNumber));
    }

    public void createNewCredit(Long applicationId){
        creditRepository.addCredit(applicationId, CreditStatus.AWAITING_CONFIRMATION.toString());
    }

    public void updateCreditStatusToActive(Long contractNumber){
        creditRepository.updateCreditStatus(contractNumber, CreditStatus.ACTIVE.toString());
    }

    public void updateCreditStatusToExpired(Long contractNumber){
        creditRepository.updateCreditStatus(contractNumber, CreditStatus.EXPIRED.toString());
    }
    public void confirmCredit(Long contractNumber){
        creditRepository.confirmCredit(contractNumber);
        Credit credit = creditRepository.findCreditByContractNumber(contractNumber);
        if (credit.getApprovedApplication().getDownPayment() != null 
                && credit.getApprovedApplication().getDownPayment().doubleValue() > 0){
            credit.setCurrentAmount(BigDecimal.valueOf(credit.getCurrentAmount().doubleValue() - credit.getApprovedApplication().getDownPayment().doubleValue()));
            Transaction transaction = new Transaction();
            transaction.setAmount(credit.getApprovedApplication().getDownPayment());
            transaction.setTransactionDate(LocalDateTime.now());
            transaction.setDescription(TransactionType.DOWN_PAYMENT);
            transaction.setCredit(credit);
            List<Transaction> transactions = new ArrayList<>();
            transactions.add(transaction);
            credit.setTransactions(transactions);
            creditRepository.save(credit);

        }
    }
}
