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
@RequestMapping("/main")
public class ProductCatalogController {
    private final CreditProductService creditProductService;

    @GetMapping("/client")
    public String showClientProductCatalogForm(Model model) {
        List<CreditProductDTO> creditProductDTOS = creditProductService.getAllCreditProducts();
        model.addAttribute("productsList", creditProductDTOS);
        return "client_credit_products_catalog";
    }
    @GetMapping("/manager")
    public String showManagerProductCatalogForm(Model model) {
        List<CreditProductDTO> creditProductDTOS = creditProductService.getAllCreditProducts();
        model.addAttribute("productsList", creditProductDTOS);
        return "manager_credit_products_catalog";
    }
    @GetMapping("/admin")
    public String showAdminProductCatalogForm(Model model) {
        List<CreditProductDTO> creditProductDTOS = creditProductService.getAllCreditProducts();
        model.addAttribute("productsList", creditProductDTOS);
        return "admin_credit_products_catalog";
    }


}