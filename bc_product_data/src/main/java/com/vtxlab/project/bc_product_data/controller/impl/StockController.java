package com.vtxlab.project.bc_product_data.controller.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.vtxlab.project.bc_product_data.controller.StockOperation;
import com.vtxlab.project.bc_product_data.model.CompanyProfile;
import com.vtxlab.project.bc_product_data.model.Quote;
import com.vtxlab.project.bc_product_data.model.StockDTO;
import com.vtxlab.project.bc_product_data.model.response.CompanyProfileResponseDTO;
import com.vtxlab.project.bc_product_data.model.response.QuoteResponseDTO;
import com.vtxlab.project.bc_product_data.service.StockService;

@RestController
@RequestMapping("/api/v1")
public class StockController implements StockOperation {

  @Autowired
  private StockService stockService;

  @Override
  public QuoteResponseDTO getQuote(String symbol) {
    return stockService.getQuote(symbol);
  }

  @Override
  public CompanyProfileResponseDTO getProfile(String symbol) {
    return stockService.getProfile(symbol);
  }
}
