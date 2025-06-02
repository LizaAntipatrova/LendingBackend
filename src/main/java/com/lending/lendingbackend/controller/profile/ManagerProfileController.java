package com.lending.lendingbackend.controller.profile;

import com.lending.lendingbackend.auth.services.AuthService;
import com.lending.lendingbackend.data.entity.ApplicationStatus;
import com.lending.lendingbackend.dto.CreditApplicationDTO;
import com.lending.lendingbackend.dto.CreditDTO;
import com.lending.lendingbackend.dto.ManagerDTO;
import com.lending.lendingbackend.service.CreditService;
import com.lending.lendingbackend.service.ManagerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/manager/profile")
public class ManagerProfileController {
    private final ManagerService managerService;
    private final CreditService creditService;
    private final AuthService authService;

    @GetMapping()
    public String showProfilePage(Model model, @RequestHeader("Cookie") String cookie) {
        Long userId = authService.getUserIdFromCookie(cookie);
        ManagerDTO managerDTO = managerService.getManagerDTOByUserId(userId);
        List<CreditApplicationDTO> creditApplications = managerService.getManagersApplicationListDTOByUserId(userId);
        List<CreditDTO> credits = creditService.getManagersCreditListDTOByUserId(userId);
        model.addAttribute("managerDTO", managerDTO);
        model.addAttribute("creditList", credits);
        model.addAttribute("applicationList", creditApplications);
        model.addAttribute("applicationStatusTitles", ApplicationStatus.getStatusTitles());
        return "manager_profile";
    }

}
