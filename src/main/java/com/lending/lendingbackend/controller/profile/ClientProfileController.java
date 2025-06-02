package com.lending.lendingbackend.controller.profile;

import com.lending.lendingbackend.auth.services.AuthService;
import com.lending.lendingbackend.data.entity.ApplicationStatus;
import com.lending.lendingbackend.dto.ClientDTO;
import com.lending.lendingbackend.dto.CreditApplicationDTO;
import com.lending.lendingbackend.dto.CreditDTO;
import com.lending.lendingbackend.service.ClientService;
import com.lending.lendingbackend.service.CreditService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/client/profile")
public class ClientProfileController {
    private final ClientService clientService;
    private final CreditService creditService;
    private final AuthService authService;

    @GetMapping()
    public String showProfilePage(Model model, @RequestHeader("Cookie") String cookie) {
        Long userId = authService.getUserIdFromCookie(cookie);
        ClientDTO clientDTO = clientService.getClientDTOByUserId(userId);
        List<CreditApplicationDTO> creditApplications = clientService.getClientsApplicationListDTOByUserId(userId);
        List<CreditDTO> credits = creditService.getClientsCreditListDTOByUserId(userId);
        model.addAttribute("clientDTO", clientDTO);
        model.addAttribute("creditList", credits);
        model.addAttribute("applicationList", creditApplications);
        model.addAttribute("applicationStatusTitles", ApplicationStatus.getStatusTitles());
        return "client_profile";
    }

}
