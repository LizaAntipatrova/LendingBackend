package com.lending.lendingbackend.controller.auth;


import com.lending.lendingbackend.auth.cookie.SessionCookieProvider;
import com.lending.lendingbackend.auth.services.AuthService;
import com.lending.lendingbackend.auth.services.CustomResponse;
import com.lending.lendingbackend.dto.LoginDTO;
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
    private final AuthService authService;

    @GetMapping("/sign-in")
    public String showSignInForm(Model model) {
        model.addAttribute("loginDTO", new LoginDTO());
        return "admin_login";
    }

    @PostMapping("/sign-in")
    public String signIn(@ModelAttribute("loginDTO") LoginDTO loginDTO, Model model, HttpServletResponse response) {
        CustomResponse authResponse = authService.signIn(loginDTO);
        SessionCookieProvider.setUpAdminSessionCookie(response, authResponse.getCookieSessionId());
        return "redirect:/admin/main";
    }

}
