package com.lending.lendingbackend.controller.main;

import com.lending.lendingbackend.dto.CreditProductDTO;
import com.lending.lendingbackend.service.CreditProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/client/main")
public class ClientMainPageController {
    private final CreditProductService creditProductService;

    @GetMapping()
    public String showMainPage(Model model) {
        List<CreditProductDTO> creditProductDTOS = creditProductService.getAllCreditProducts();
        model.addAttribute("productsList", creditProductDTOS);
        return "client_main";
    }

}
