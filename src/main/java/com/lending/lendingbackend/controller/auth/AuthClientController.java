package com.lending.lendingbackend.controller.auth;

import com.lending.lendingbackend.dto.LoginDTO;
import com.lending.lendingbackend.dto.RegistrationDTO;
import com.lending.lendingbackend.service.ui.auth.AuthUIClientService;
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

    private final AuthUIClientService authUIService;

    //показ формы входа студента
    @GetMapping("/sign-in")
    public String showSignInForm(Model model) {
        return authUIService.getSignInForm(model);
    }

    //обработка данных формы входа студента
    @PostMapping("/sign-in")
    public String signIn(@ModelAttribute("loginDTO") LoginDTO loginDTO, HttpServletResponse response) {
        return authUIService.postSignIn(loginDTO, response);
    }

    //показ формы регистрации
    @GetMapping("/sign-up")
    public String showSignUpForm(Model model) {
        return authUIService.getSignUpForm(model);
    }

    //обработка формы регистрации
    @PostMapping("/sign-up")
    public String signUp(@ModelAttribute("registrationDTO") RegistrationDTO registrationDTO) {
        return authUIService.postSingUpForm(registrationDTO);
    }


}
