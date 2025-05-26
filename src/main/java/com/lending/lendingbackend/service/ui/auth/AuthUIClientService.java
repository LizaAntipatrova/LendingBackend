package com.lending.lendingbackend.service.ui.auth;


import com.lending.lendingbackend.auth.cookie.SessionCookieProvider;
import com.lending.lendingbackend.auth.services.AuthService;
import com.lending.lendingbackend.auth.services.CustomResponse;
import com.lending.lendingbackend.dto.LoginDTO;
import com.lending.lendingbackend.dto.RegistrationDTO;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

@Service
@RequiredArgsConstructor
public class AuthUIClientService {

    private final AuthService authService;


    public String getSignInForm(Model model) {
        model.addAttribute("loginDTO", new LoginDTO());
        return "client_login";
    }

    public String postSignIn(LoginDTO loginDTO, HttpServletResponse response) {
        CustomResponse authResponse = authService.signIn(loginDTO);
        SessionCookieProvider.setUpClientSessionCookie(response, authResponse.getCookieSessionId());
        return "redirect:/client/main";
    }

    public String getSignUpForm(Model model) {
        model.addAttribute("registrationDTO", new RegistrationDTO());
        return "client_registration";
    }

    public String postSingUpForm(RegistrationDTO registrationDTO) {
        authService.signUp(registrationDTO);
        return "redirect:/auth/client/sign-in";
    }
}
