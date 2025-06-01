package com.lending.lendingbackend.controller.product;

import com.lending.lendingbackend.auth.services.AuthService;
import com.lending.lendingbackend.dto.CreditCategoryDTO;
import com.lending.lendingbackend.dto.CreditProductDTO;
import com.lending.lendingbackend.service.CreditCategoryService;
import com.lending.lendingbackend.service.CreditProductService;
import com.lending.lendingbackend.service.ManagerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping()
public class ProductController {
    private final CreditProductService creditProductService;
    private final ManagerService managerService;
    private final AuthService authService;
    private final CreditCategoryService creditCategoryService;

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
        if (managerService.existManagerSpecialization(managerUserId, id)) {
            return "product_management_look";
        } else {
            return "redirect:/main/product?id=" + id;
        }
    }

    @GetMapping("/manager/product/edit")
    public String showManagerEditProductForm(Model model, @RequestParam("id") Long id, @RequestHeader("Cookie") String cookie) {
        Long managerUserId = authService.getUserIdFromCookie(cookie);
        CreditProductDTO creditProductDTO = creditProductService.getCreditProductDTOByCreditProductCode(id);
        model.addAttribute("productDTO", creditProductDTO);
        if (managerService.existManagerSpecialization(managerUserId, id)) {
            return "product_edit";
        }
        return "redirect:/main/product?id=" + id;
    }

    @GetMapping("/manager/product/new")
    public String showManagerCreateProductForm(Model model, @RequestHeader("Cookie") String cookie) {
        Long managerUserId = authService.getUserIdFromCookie(cookie);
        List<CreditCategoryDTO> creditCategoryDTOS = managerService.getCategoryDTOByManagerUserId(managerUserId);
        model.addAttribute("categories", creditCategoryDTOS);
        model.addAttribute("productDTO", new CreditProductDTO());

        return "product_create";
    }
    @PostMapping("/manager/product/new")
    public String createManagerProduct(@ModelAttribute("productDTO") CreditProductDTO creditProductDTO) {
        creditProductService.createCreditProductByCreditProductDTO(creditProductDTO);
        return "redirect:/manager/main";
    }

    @PostMapping("/manager/product/edit")
    public String editManagerProduct(@ModelAttribute("productDTO") CreditProductDTO creditProductDTO, @RequestParam("id") Long id) {
        creditProductDTO.setCode(id);
        creditProductService.updateCreditProductByCreditProductDTO(creditProductDTO);
        return "redirect:/manager/product?id=" + id;
    }

    @PostMapping("/manager/product/delete")
    public String deleteManagerProduct(@RequestParam("id") Long id) {
        creditProductService.deleteCreditProductByProductCode(id);
        return "redirect:/manager/main";
    }



}
