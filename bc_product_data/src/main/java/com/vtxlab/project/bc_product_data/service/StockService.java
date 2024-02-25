package com.vtxlab.project.bc_product_data.service;

import com.vtxlab.project.bc_product_data.model.CompanyProfile;
import com.vtxlab.project.bc_product_data.model.response.QuoteResponseDTO;

public interface StockService {
  QuoteResponseDTO getQuote(String symbol);

  CompanyProfile getProfile(String symbol);

}
