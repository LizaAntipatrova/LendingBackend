package com.lending.lendingbackend.controller.application;

import com.lending.lendingbackend.auth.services.AuthService;
import com.lending.lendingbackend.data.entity.ApplicationStatus;
import com.lending.lendingbackend.data.entity.PaymentType;
import com.lending.lendingbackend.dto.CreditApplicationDTO;
import com.lending.lendingbackend.dto.CreditProductDTO;
import com.lending.lendingbackend.service.CreditApplicationService;
import com.lending.lendingbackend.service.CreditProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/client/application")
public class ClientApplicationController {
    private final CreditProductService creditProductService;
    private final CreditApplicationService creditApplicationService;
    private final AuthService authService;

    @GetMapping("/new")
    public String showCreateApplicationPage(Model model) {
        List<CreditProductDTO> creditProducts = creditProductService.getAllCreditProducts();
        model.addAttribute("listProducts", creditProducts);
        model.addAttribute("paymentTypeList", PaymentType.getTypeTitles());
        model.addAttribute("creditApplicationDTO", new CreditApplicationDTO());
        return "client_apply_for_credit";
    }

    @PostMapping("/new")
    public String createApplication(@RequestHeader("Cookie") String cookie, @ModelAttribute("creditApplicationDTO") CreditApplicationDTO creditApplicationDTO) {
        Long userId = authService.getUserIdFromCookie(cookie);
        creditApplicationDTO.setClientId(userId);
        creditApplicationService.createCreditApplication(creditApplicationDTO);
        return "redirect:/client/main";
    }

    @GetMapping()
    public String showApplicationPage(Model model, @RequestParam("id") Long id) {
        CreditApplicationDTO creditApplicationDTO = creditApplicationService.getCreditApplicationDTOByApplicationId(id);
        model.addAttribute("applicationStatusList", ApplicationStatus.getStatusTitles());
        model.addAttribute("paymentTypeList", PaymentType.getTypeTitles());
        model.addAttribute("creditApplicationDTO", creditApplicationDTO);
        return "application_look";
    }
}
