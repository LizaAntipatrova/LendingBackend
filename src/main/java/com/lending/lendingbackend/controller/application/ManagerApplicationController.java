package com.lending.lendingbackend.controller.application;

import com.lending.lendingbackend.auth.services.AuthService;
import com.lending.lendingbackend.data.entity.ApplicationStatus;
import com.lending.lendingbackend.data.entity.PaymentType;
import com.lending.lendingbackend.dto.ClientDTO;
import com.lending.lendingbackend.dto.CreditApplicationDTO;
import com.lending.lendingbackend.dto.CreditProductDTO;
import com.lending.lendingbackend.service.ClientService;
import com.lending.lendingbackend.service.CreditApplicationService;
import com.lending.lendingbackend.service.CreditProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/manager/application")
public class ManagerApplicationController {
    private final CreditProductService creditProductService;
    private final CreditApplicationService creditApplicationService;
    private final ClientService clientService;
    private final AuthService authService;

    @GetMapping("/new")
    public String showCreateApplicationPage(Model model, @RequestHeader("Cookie") String cookie) {
        Long userId = authService.getUserIdFromCookie(cookie);
        List<CreditProductDTO> creditProducts = creditProductService.getCreditProductsByManagerUserId(userId);
        List<ClientDTO> clientDTOS = clientService.getAllClientDTOs();
        model.addAttribute("listClients", clientDTOS);
        model.addAttribute("listProducts", creditProducts);
        model.addAttribute("paymentTypeList", PaymentType.getTypeTitles());
        model.addAttribute("creditApplicationDTO", new CreditApplicationDTO());
        return "manager_apply_for_credit";
    }

    @PostMapping("/new")
    public String createApplication(@RequestHeader("Cookie") String cookie, @ModelAttribute("creditApplicationDTO") CreditApplicationDTO creditApplicationDTO) {
        Long userId = authService.getUserIdFromCookie(cookie);
        creditApplicationDTO.setManagerId(userId);
        creditApplicationService.createCreditApplication(creditApplicationDTO);
        return "redirect:/manager/main";
    }
    @GetMapping()
    public String showApplicationPage(Model model, @RequestParam("id") Long id) {
        CreditApplicationDTO creditApplicationDTO = creditApplicationService.getCreditApplicationDTOByApplicationId(id);
        model.addAttribute("applicationStatusList", ApplicationStatus.getStatusTitles());
        model.addAttribute("paymentTypeList", PaymentType.getTypeTitles());
        model.addAttribute("creditApplicationDTO", creditApplicationDTO);
        if(creditApplicationDTO.getStatus().equals(ApplicationStatus.IN_PROGRESS)){
            return "manager_application_look";
        }
        return "application_look";
    }

    @PostMapping("/approved")
    public String approvedApplication(@RequestParam("id") Long id) {
        creditApplicationService.approvedApplication(id);
        return "redirect:/manager/application?id=" + id;
    }
    @PostMapping("/rejected")
    public String rejectedApplication(@RequestParam("id") Long id) {
        creditApplicationService.rejectedApplication(id);
        return "redirect:/manager/application?id=" + id;
    }
}
