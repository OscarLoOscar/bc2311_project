package com.vtxlab.project.bc_product_data.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import com.vtxlab.project.bc_product_data.model.CompanyProfile;
import com.vtxlab.project.bc_product_data.model.Quote;
import com.vtxlab.project.bc_product_data.model.response.QuoteResponseDTO;
import com.vtxlab.project.bc_product_data.service.StockService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class StockServiceImpl implements StockService {

  @Autowired
  private RestTemplate restTemplate;

  @Autowired
  @Qualifier("finnhubQuoteUriString")
  private UriComponentsBuilder finnhubQuoteUriString;

  @Autowired
  @Qualifier("finnhubProfileUriString")
  private UriComponentsBuilder finnhubProfileUriString;


  @Override
  public QuoteResponseDTO getQuote(String symbol) {
    log.info("Service getQuote: " + finnhubQuoteUriString
        .replaceQueryParam("symbol", symbol).build(false).toUriString());
    Quote data = restTemplate.getForObject(finnhubQuoteUriString
        .replaceQueryParam("symbol", symbol).build(false).toUriString(),
        Quote.class);
    return QuoteResponseDTO.builder()//
        .c(data.getData().getC())//
        .d(data.getData().getD())//
        .dp(data.getData().getDp())//
        .h(data.getData().getH())//
        .l(data.getData().getL())//
        .o(data.getData().getO())//
        .pc(data.getData().getPc())//
        .t(data.getData().getT())//
        .build();
  }

  @Override
  public CompanyProfile getProfile(String symbol) {
    log.info("Service getProfile: " + finnhubProfileUriString
        .replaceQueryParam("symbol", symbol).build(false).toUriString());
    return restTemplate.getForObject(finnhubProfileUriString
        .replaceQueryParam("symbol", symbol).build(false).toUriString(),
        CompanyProfile.class);
  }
}
