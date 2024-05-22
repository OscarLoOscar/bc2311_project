package com.vtxlab.project.bc_product_data.controller;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;
import com.vtxlab.project.bc_product_data.model.CoinMarketRespDto;

public interface CoinOperation {

  // @GetMapping("/coin")
  ModelAndView getAllCoinData();

  @GetMapping("/coin")
  String getAllCoinData(Model model);

    @GetMapping("/market")
  @ResponseStatus(HttpStatus.OK)
  public List<CoinMarketRespDto> getMarketData();

  @GetMapping("/coinData")
  @ResponseStatus(HttpStatus.OK)
  public CoinMarketRespDto getMarketDataByCoinID(@RequestParam String symbol);

}
