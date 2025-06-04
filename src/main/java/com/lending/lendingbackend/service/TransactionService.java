package com.lending.lendingbackend.service;

import com.lending.lendingbackend.data.entity.Credit;
import com.lending.lendingbackend.data.entity.CreditStatus;
import com.lending.lendingbackend.data.entity.Transaction;
import com.lending.lendingbackend.data.repository.TransactionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class TransactionService {
    private final TransactionRepository transactionRepository;
    private final CreditService creditService;

    @Transactional
    @Scheduled(cron = "0 0 0 1 * ?")  // 1-е число каждого месяца в полночь
    public void chargeInterest() {
        log.info("Запуск chargeInterest() в {}", LocalDateTime.now());
        creditService.getAllCredits().stream()
                .filter(credit -> (credit.getStatus().equals(CreditStatus.ACTIVE) || credit.getStatus().equals(CreditStatus.EXPIRED)))
                .map(Credit::getContractNumber).forEach(transactionRepository::chargeInterest);
    }

    @Transactional
    @Scheduled(cron = "0 0 0 * * ?") // Каждый день в полночь
    public void chargePenalty() {
        log.info("Запуск chargePenalty() в {}", LocalDateTime.now());
        List<Credit> credits = creditService.getAllCredits();

        for (Credit currCredit : credits) {

            List<Transaction> transactions = currCredit.getTransactions();
            if (transactions == null || transactions.isEmpty()) {
                if (ChronoUnit.MONTHS.between(currCredit.getContractDate(), LocalDateTime.now()) > 1) {
                    creditService.updateCreditStatusToExpired(currCredit.getContractNumber());
                    transactionRepository.chargePenalty(currCredit.getContractNumber());
                }
                continue; // Пропускаем кредиты без транзакций
            }

            // Безопасное получение даты последней транзакции
            Optional<LocalDateTime> lastTransactionTimeOpt = transactions.stream()
                    .map(Transaction::getTransactionDate)
                    .max(LocalDateTime::compareTo);

            LocalDateTime lastTransactionTime = lastTransactionTimeOpt.get();

            if (currCredit.getStatus().equals(CreditStatus.EXPIRED)) {
                if (ChronoUnit.MONTHS.between(lastTransactionTime, LocalDateTime.now()) < 1) {
                    creditService.updateCreditStatusToActive(currCredit.getContractNumber());
                } else {
                    transactionRepository.chargePenalty(currCredit.getContractNumber());
                }
            } else if (currCredit.getStatus().equals(CreditStatus.ACTIVE)) {
                if (ChronoUnit.MONTHS.between(lastTransactionTime, LocalDateTime.now()) > 1 &&
                        ChronoUnit.MONTHS.between(currCredit.getContractDate(), LocalDateTime.now()) > 1) {
                    creditService.updateCreditStatusToExpired(currCredit.getContractNumber());
                    transactionRepository.chargePenalty(currCredit.getContractNumber());
                }
            }
        }
    }
}


