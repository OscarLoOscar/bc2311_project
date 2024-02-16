package com.vtxlab.project.bc_product_quote.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.vtxlab.project.bc_product_quote.model.StockDTO;

public interface StockOperation {
  @GetMapping("/stock")
  StockDTO getStock(@RequestParam String symbol);
}
