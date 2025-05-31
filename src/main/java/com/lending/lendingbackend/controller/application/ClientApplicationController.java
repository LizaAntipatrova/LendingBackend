package com.lending.lendingbackend.controller.application;

import com.lending.lendingbackend.data.entity.PaymentType;
import com.lending.lendingbackend.dto.ClientDTO;
import com.lending.lendingbackend.dto.CreditApplicationDTO;
import com.lending.lendingbackend.dto.CreditDTO;
import com.lending.lendingbackend.dto.CreditProductDTO;
import com.lending.lendingbackend.service.CreditProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/client/application")
public class ClientApplicationController {
    private final CreditProductService creditProductService;

    @GetMapping("/new")
    public String showCreateApplicationPage(Model model, @RequestHeader("Cookie") String cookie) {
        List<CreditProductDTO> creditProducts = creditProductService.getAllCreditProducts();
        model.addAttribute("listProducts", creditProducts);
        model.addAttribute("paymentTypeList", PaymentType.getTypeTitles());
        model.addAttribute("creditApplicationDTO", new CreditApplicationDTO());
        return "client_apply_for_credit";
    }
}
