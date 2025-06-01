package com.lending.lendingbackend.controller.product;

import com.lending.lendingbackend.auth.services.AuthService;
import com.lending.lendingbackend.dto.CreditProductDTO;
import com.lending.lendingbackend.service.CreditProductService;
import com.lending.lendingbackend.service.ManagerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping()
public class ProductController {
    private final CreditProductService  creditProductService;
    private final ManagerService managerService;
    private final AuthService authService;

    @GetMapping("/main/product")
    public String showMainProductCatalogForm(Model model, @RequestParam("id") Long id) {
        CreditProductDTO creditProductDTO = creditProductService.getCreditProductDTOByCreditProductCode(id);
        model.addAttribute("productDTO", creditProductDTO);
        return "product_look";
    }
    @GetMapping("/manager/product")
    public String showManagerProductForm(Model model, @RequestParam("id") Long id, @RequestHeader("Cookie") String cookie) {
        Long managerUserId = authService.getUserIdFromCookie(cookie);
        CreditProductDTO creditProductDTO = creditProductService.getCreditProductDTOByCreditProductCode(id);
        model.addAttribute("productDTO", creditProductDTO);
        if(managerService.existManagerSpecialization(managerUserId, id)){
            return "product_management_look";
        }else{
            return "product_look";
        }
    }

}
