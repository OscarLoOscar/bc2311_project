package com.vtxlab.project.bc_product_quote.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import com.vtxlab.project.bc_product_quote.infra.Mapper;
import com.vtxlab.project.bc_product_quote.model.StockDTO;
import com.vtxlab.project.bc_product_quote.service.StockService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class StockServiceImpl implements StockService {

  @Autowired
  private RestTemplate restTemplate;

  @Autowired
  private Mapper mapper;

  @Autowired
  @Qualifier("coingeckoUriString")
  private UriComponentsBuilder coingeckoUriString;

  @Autowired
  @Qualifier("finnhubStockUriString")
  private UriComponentsBuilder finnhubStockUriString;

  public StockDTO getStock(String symbol) {
log.info("Service : " + finnhubStockUriString.replaceQueryParam("symbol",symbol).build(false).toUriString());
    return restTemplate.getForObject(
        finnhubStockUriString.replaceQueryParam("symbol",symbol).build(false).toUriString(),
        StockDTO.class);
  }
}
