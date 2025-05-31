package com.lending.lendingbackend.controller.auth;

import com.lending.lendingbackend.auth.cookie.SessionCookieProvider;
import com.lending.lendingbackend.auth.services.AuthService;
import com.lending.lendingbackend.auth.services.CustomResponse;
import com.lending.lendingbackend.dto.LoginDTO;
import com.lending.lendingbackend.dto.RegistrationDTO;
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
@RequestMapping("/auth/client")
public class AuthClientController {


    private final AuthService authService;

    //показ формы входа студента
    @GetMapping("/sign-in")
    public String showSignInForm(Model model) {
        model.addAttribute("loginDTO", new LoginDTO());
        return "client_login";
    }

    //обработка данных формы входа студента
    @PostMapping("/sign-in")
    public String signIn(@ModelAttribute("loginDTO") LoginDTO loginDTO, HttpServletResponse response) {
        CustomResponse authResponse = authService.signIn(loginDTO);
        SessionCookieProvider.setUpClientSessionCookie(response, authResponse.getCookieSessionId());
        return "redirect:/client/main";
    }

    //показ формы регистрации
    @GetMapping("/sign-up")
    public String showSignUpForm(Model model) {
        model.addAttribute("registrationDTO", new RegistrationDTO());
        return "client_registration";
    }

    //обработка формы регистрации
    @PostMapping("/sign-up")
    public String signUp(@ModelAttribute("registrationDTO") RegistrationDTO registrationDTO) {
        authService.signUp(registrationDTO);
        return "redirect:/auth/client/sign-in";
    }


}
