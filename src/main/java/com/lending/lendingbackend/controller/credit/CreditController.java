package com.lending.lendingbackend.controller.credit;

import com.lending.lendingbackend.data.entity.CreditStatus;
import com.lending.lendingbackend.data.entity.PaymentType;
import com.lending.lendingbackend.data.entity.Transaction;
import com.lending.lendingbackend.dto.CreditDTO;
import com.lending.lendingbackend.service.CreditContractGenerateDocumentService;
import com.lending.lendingbackend.service.CreditService;
import com.lending.lendingbackend.service.CreditTransactionGenerateDocumentService;
import com.lending.lendingbackend.service.TransactionService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.OutputStream;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class CreditController {
    private final CreditService creditService;
    private final CreditTransactionGenerateDocumentService creditTransactionGenerateDocumentService;
    private final CreditContractGenerateDocumentService creditContractGenerateDocumentService;
    private final TransactionService transactionService;

    @GetMapping("/client/credit")
    public String showClientCredit(Model model, @RequestParam("id") Long id) {
        CreditDTO creditDTO = creditService.getCreditByContractNumber(id);
        model.addAttribute("creditStatusList", CreditStatus.getStatusTitles());
        model.addAttribute("paymentTypeList", PaymentType.getTypeTitles());
        model.addAttribute("creditDTO", creditDTO);
        return "credit_look";
    }

    @SneakyThrows
    @PostMapping("/credit/contract")
    public void getTransactionList(HttpServletResponse response, @RequestParam("id") Long id) {
        // Устанавливаем заголовки для ответа
        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition",
                "attachment; filename=credit_contract_" + id + ".pdf");

        // Получаем OutputStream для записи PDF
        OutputStream out = response.getOutputStream();
        CreditDTO creditDTO = creditService.getCreditByContractNumber(id);

        // Генерируем PDF и пишем прямо в OutputStream
        creditContractGenerateDocumentService.generateCreditContract(out, creditDTO);

        out.flush();

    }
    @SneakyThrows
    @PostMapping("/credit/transactions")
    public void getCreditContract(HttpServletResponse response, @RequestParam("id") Long id) {
        // Устанавливаем заголовки для ответа
        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition",
                "attachment; filename=credit_statement_" + id + ".pdf");

        // Получаем OutputStream для записи PDF
        OutputStream out = response.getOutputStream();
        List<Transaction> transactions = transactionService.getTransactionsByContractNumber(id);
        CreditDTO creditDTO = creditService.getCreditByContractNumber(id);

        // Генерируем PDF и пишем прямо в OutputStream
        creditTransactionGenerateDocumentService.generateCreditStatement(out, id, transactions, creditDTO.getManagerName());

        out.flush();

    }

    @GetMapping("/manager/credit")
    public String showManagerCredit(Model model, @RequestParam("id") Long id) {
        CreditDTO creditDTO = creditService.getCreditByContractNumber(id);
        model.addAttribute("creditStatusList", CreditStatus.getStatusTitles());
        model.addAttribute("paymentTypeList", PaymentType.getTypeTitles());
        model.addAttribute("creditDTO", creditDTO);
        if (creditDTO.getStatus().equals(CreditStatus.AWAITING_CONFIRMATION)) {
            return "manager_credit_look";
        }
        return "credit_look";
    }

    @PostMapping("/manager/credit/confirm")
    public String confirmCredit(@RequestParam("id") Long id) {
        creditService.confirmCredit(id);
        return "redirect:/manager/credit?id=" + id;
    }

}
