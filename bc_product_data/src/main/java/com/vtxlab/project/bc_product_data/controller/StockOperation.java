package com.vtxlab.project.bc_product_data.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.vtxlab.project.bc_product_data.annotation.stock.StockSymbolCheck;
import com.vtxlab.project.bc_product_data.model.CompanyProfile;
import com.vtxlab.project.bc_product_data.model.response.QuoteResponseDTO;

public interface StockOperation {
  @GetMapping("/quote")
  QuoteResponseDTO getQuote(@StockSymbolCheck @RequestParam String symbol);

  @GetMapping("/profile")
  CompanyProfile getProfile(@StockSymbolCheck @RequestParam String symbol);
}
