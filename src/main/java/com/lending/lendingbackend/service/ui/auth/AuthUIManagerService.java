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
public class AuthUIManagerService {

    private final AuthService authService;


    public String getSignInForm(Model model) {
        model.addAttribute("loginDTO", new LoginDTO());
        return "manager_login";
    }

    public String postSignIn(Model model, LoginDTO loginDTO, HttpServletResponse response) {
        CustomResponse authResponse = authService.signIn(loginDTO);
        SessionCookieProvider.setUpManagerSessionCookie(response, authResponse.getCookieSessionId());
        return "redirect:/manager/main";
    }

}