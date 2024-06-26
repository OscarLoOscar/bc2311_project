package com.vtxlab.project.bc_product_data.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import com.vtxlab.project.bc_product_data.model.response.CoinListResponseDTO;
import com.vtxlab.project.bc_product_data.model.response.StockListResponseDTO;

public interface ValidListOperation {
  @GetMapping("/coin")
  @CrossOrigin
  @ResponseStatus(HttpStatus.OK)
  CoinListResponseDTO getCoinList();

  @GetMapping("/stock")
  @CrossOrigin
  @ResponseStatus(HttpStatus.OK)
  StockListResponseDTO getStockList();

  @DeleteMapping("/coin")
  @ResponseStatus(HttpStatus.ACCEPTED)
  boolean deleteCoin(String coinId);

  @DeleteMapping("/stock")
  @ResponseStatus(HttpStatus.ACCEPTED)
  boolean deleteStock(String stockId);

}
