package com.vtxlab.project.bc_product_quote.service;

import com.vtxlab.project.bc_product_quote.model.CompanyProfile;
import com.vtxlab.project.bc_product_quote.model.Quote;
import com.vtxlab.project.bc_product_quote.model.StockDTO;

public interface StockService {
  StockDTO getStock(String symbol);

  Quote getQuote(String symbol);

  CompanyProfile getProfile(String symbol);

}
