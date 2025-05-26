package com.lending.lendingbackend.service.ui.auth;


import com.lending.lendingbackend.auth.cookie.SessionCookieProvider;
import com.lending.lendingbackend.auth.services.AuthService;
import com.lending.lendingbackend.auth.services.CustomResponse;
import com.lending.lendingbackend.dto.LoginDTO;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

@Service
@RequiredArgsConstructor
public class AuthUIAdminService {

    private final AuthService authService;


    public String getSignInForm(Model model) {
        model.addAttribute("loginDTO", new LoginDTO());
        return "admin_login";
    }

    public String postSignIn(Model model, LoginDTO loginDTO, HttpServletResponse response) {
        CustomResponse authResponse = authService.signIn(loginDTO);
        SessionCookieProvider.setUpAdminSessionCookie(response, authResponse.getCookieSessionId());
        return "redirect:/admin/main";
    }


}