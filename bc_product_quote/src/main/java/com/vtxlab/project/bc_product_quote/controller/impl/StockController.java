package com.vtxlab.project.bc_product_quote.controller.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.vtxlab.project.bc_product_quote.controller.StockOperation;
import com.vtxlab.project.bc_product_quote.model.CompanyProfile;
import com.vtxlab.project.bc_product_quote.model.Quote;
import com.vtxlab.project.bc_product_quote.model.StockDTO;
import com.vtxlab.project.bc_product_quote.service.StockService;

@RestController
@RequestMapping("/api/v1")
public class StockController implements StockOperation {

  @Autowired
  private StockService stockService;

  @Override
  public StockDTO getStock(String symbol) {
    return stockService.getStock(symbol);
  }

  @Override
  public Quote getQuote(String symbol) {
    return stockService.getQuote(symbol);
  }

  @Override
  public CompanyProfile getProfile(String symbol) {
    return stockService.getProfile(symbol);
  }
}
