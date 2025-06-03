package com.lending.lendingbackend.controller.credit;

import com.lending.lendingbackend.data.entity.ApplicationStatus;
import com.lending.lendingbackend.data.entity.CreditStatus;
import com.lending.lendingbackend.data.entity.PaymentType;
import com.lending.lendingbackend.dto.CreditApplicationDTO;
import com.lending.lendingbackend.dto.CreditDTO;
import com.lending.lendingbackend.service.CreditService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class CreditController {
    private final CreditService creditService;
    @GetMapping("/client/credit")
    public String showClientCredit(Model model, @RequestParam("id") Long id) {
        CreditDTO creditDTO = creditService.getCreditByContractNumber(id);
        model.addAttribute("creditStatusList", CreditStatus.getStatusTitles());
        model.addAttribute("paymentTypeList", PaymentType.getTypeTitles());
        model.addAttribute("creditDTO", creditDTO);
        return "credit_look";
    }
    @GetMapping("/manager/credit")
    public String showManagerCredit(Model model, @RequestParam("id") Long id) {
        CreditDTO creditDTO = creditService.getCreditByContractNumber(id);
        model.addAttribute("creditStatusList", CreditStatus.getStatusTitles());
        model.addAttribute("paymentTypeList", PaymentType.getTypeTitles());
        model.addAttribute("creditDTO", creditDTO);
        if(creditDTO.getStatus().equals(CreditStatus.AWAITING_CONFIRMATION)){
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
