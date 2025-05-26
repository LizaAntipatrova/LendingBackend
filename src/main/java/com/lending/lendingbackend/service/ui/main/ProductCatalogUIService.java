package com.lending.lendingbackend.service.ui.main;

import com.lending.lendingbackend.dto.CreditProductDTO;
import com.lending.lendingbackend.service.CreditProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductCatalogUIService {
    private final CreditProductService creditProductService;

    public String getCreditProductCatalog(Model model){
        List<CreditProductDTO> creditProductDTOS = creditProductService.getAllCreditProducts();
        model.addAttribute("productsList", creditProductDTOS);
        return "credit_products_catalog";
    }
}
