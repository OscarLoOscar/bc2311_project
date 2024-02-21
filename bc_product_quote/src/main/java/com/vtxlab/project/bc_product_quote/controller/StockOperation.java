package com.vtxlab.project.bc_product_quote.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.vtxlab.project.bc_product_quote.annotation.stock.stockSymbolCheck;
import com.vtxlab.project.bc_product_quote.model.CompanyProfile;
import com.vtxlab.project.bc_product_quote.model.Quote;
import com.vtxlab.project.bc_product_quote.model.StockDTO;

public interface StockOperation {
  @GetMapping("/quote")
  Quote getQuote(@stockSymbolCheck @RequestParam String symbol);

  @GetMapping("/profile")
  CompanyProfile getProfile(@stockSymbolCheck @RequestParam String symbol);

  @GetMapping("/stock")
  StockDTO getStock(@stockSymbolCheck @RequestParam String symbol);
}
