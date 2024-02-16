package com.vtxlab.project.bc_product_quote.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import com.vtxlab.project.bc_product_quote.infra.Mapper;
import com.vtxlab.project.bc_product_quote.model.CompanyProfile;
import com.vtxlab.project.bc_product_quote.model.Quote;
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

  @Qualifier("coingeckoUriBuilder")
  private UriComponentsBuilder coingeckoUriBuilder;

  @Qualifier("finnhubQuoteUriBuilder")
  private UriComponentsBuilder finnhubQuoteUriBuilder;

  public StockDTO getStock(String symbol) {
    log.info("quote : " + coingeckoUriBuilder.toUriString());
    Quote quote = restTemplate.getForObject(coingeckoUriBuilder.toUriString(),
        Quote.class);
    log.info("profile : "
        + finnhubQuoteUriBuilder.queryParam("symbol", symbol).toUriString());
    CompanyProfile profile = restTemplate.getForObject(
        finnhubQuoteUriBuilder.queryParam("symbol", symbol).toUriString(),
        CompanyProfile.class);
    return mapper.map(quote, profile);

  }
}
