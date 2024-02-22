package com.vtxlab.project.bc_product_data.service;

import com.vtxlab.project.bc_product_data.model.CompanyProfile;
import com.vtxlab.project.bc_product_data.model.Quote;
import com.vtxlab.project.bc_product_data.model.StockDTO;

public interface StockService {
  StockDTO getStock(String symbol);

  Quote getQuote(String symbol);

  CompanyProfile getProfile(String symbol);

}
