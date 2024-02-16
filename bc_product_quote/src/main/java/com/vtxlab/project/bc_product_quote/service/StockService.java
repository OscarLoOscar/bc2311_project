package com.vtxlab.project.bc_product_quote.service;

import com.vtxlab.project.bc_product_quote.model.StockDTO;

public interface StockService {
  StockDTO getStock(String symbol);
}
