package com.lending.lendingbackend.controller.main;

import com.lending.lendingbackend.service.ui.main.ProductCatalogUIService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/main")
public class ProductCatalogController {
    private final ProductCatalogUIService productCatalogUIService;

    @GetMapping()
    public String showProductCatalogForm(Model model) {
        return productCatalogUIService.getCreditProductCatalog(model);
    }

}