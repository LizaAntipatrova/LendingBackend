package com.lending.lendingbackend.controller.auth;


import com.lending.lendingbackend.dto.LoginDTO;
import com.lending.lendingbackend.service.ui.auth.AuthUIAdminService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/auth/admin")
public class AuthAdminController {
    private final AuthUIAdminService authUIService;

    @GetMapping("/sign-in")
    public String showSignInForm(Model model) {
        return authUIService.getSignInForm(model);
    }

    @PostMapping("/sign-in")
    public String signIn(@ModelAttribute("loginDTO") LoginDTO loginDTO, Model model, HttpServletResponse response) {
        return authUIService.postSignIn(model, loginDTO, response);
    }

}
