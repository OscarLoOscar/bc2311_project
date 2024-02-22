package com.vtxlab.project.bc_product_data.controller.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.vtxlab.project.bc_product_data.controller.StockOperation;
import com.vtxlab.project.bc_product_data.model.CompanyProfile;
import com.vtxlab.project.bc_product_data.model.Quote;
import com.vtxlab.project.bc_product_data.model.StockDTO;
import com.vtxlab.project.bc_product_data.service.StockService;

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
