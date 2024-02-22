package com.vtxlab.project.bc_product_data.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import com.vtxlab.project.bc_product_data.infra.Mapper;
import com.vtxlab.project.bc_product_data.model.CompanyProfile;
import com.vtxlab.project.bc_product_data.model.Quote;
import com.vtxlab.project.bc_product_data.model.StockDTO;
import com.vtxlab.project.bc_product_data.service.StockService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class StockServiceImpl implements StockService {

  @Autowired
  private RestTemplate restTemplate;

  @Autowired
  @Qualifier("coingeckoUriString")
  private UriComponentsBuilder coingeckoUriString;

  @Autowired
  @Qualifier("finnhubStockUriString")
  private UriComponentsBuilder finnhubStockUriString;

  @Autowired
  @Qualifier("finnhubQuoteUriString")
  private UriComponentsBuilder finnhubQuoteUriString;

  @Autowired
  @Qualifier("finnhubProfileUriString")
  private UriComponentsBuilder finnhubProfileUriString;

  public StockDTO getStock(String symbol) {
    log.info("Service stock: " + finnhubStockUriString
        .replaceQueryParam("symbol", symbol).build(false).toUriString());
    return restTemplate.getForObject(finnhubStockUriString
        .replaceQueryParam("symbol", symbol).build(false).toUriString(),
        StockDTO.class);
  }

  @Override
  public Quote getQuote(String symbol) {
    log.info("Service stock: " + finnhubQuoteUriString
        .replaceQueryParam("symbol", symbol).build(false).toUriString());
    return restTemplate.getForObject(finnhubQuoteUriString
        .replaceQueryParam("symbol", symbol).build(false).toUriString(),
        Quote.class);
  }

  @Override
  public CompanyProfile getProfile(String symbol) {
    log.info("Service stock: " + finnhubProfileUriString
        .replaceQueryParam("symbol", symbol).build(false).toUriString());
    return restTemplate.getForObject(finnhubProfileUriString
        .replaceQueryParam("symbol", symbol).build(false).toUriString(),
        CompanyProfile.class);
  }
}
