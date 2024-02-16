package com.vtxlab.project.bc_stock_finnhub.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import com.vtxlab.project.bc_stock_finnhub.config.RedisUtils;
import com.vtxlab.project.bc_stock_finnhub.infra.Mapper;
import com.vtxlab.project.bc_stock_finnhub.model.CompanyProfile;
import com.vtxlab.project.bc_stock_finnhub.model.Quote;
import com.vtxlab.project.bc_stock_finnhub.model.StockDTO;
import com.vtxlab.project.bc_stock_finnhub.service.FinnhubService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class FinnhubServiceImpl implements FinnhubService {

  @Autowired
  private RestTemplate restTemplate;

  @Autowired
  @Qualifier("finnhubQuoteUriBuilder")
  private UriComponentsBuilder finnhubQuoteUriBuilder;

  @Autowired
  @Qualifier("finnhubCompanyProfileUriBuilder")
  private UriComponentsBuilder finnhubCompanyProfileUriBuilder;


  @Autowired
  private RedisUtils redisUtils;

  @Autowired
  private Mapper mapper;

  @Override
  public Quote getQuote(String symbol) {
    String key = "stock:finnhub:quote:";
    Quote quote = getQuoteFromApi(symbol);
    redisUtils.set(key + symbol, quote, 60);
    return quote;
  }

  @Override
  public CompanyProfile getProfile(String symbol) {
    String key = "stock:finnhub:profile:";
    CompanyProfile companyProfile = getProfileFromApi(symbol);
    redisUtils.set(key + symbol, companyProfile, 60);
    return companyProfile;
  }

  private Quote getQuoteFromApi(String symbol) {
    log.info("Service "
        + finnhubQuoteUriBuilder.queryParam("symbol", symbol).toUriString());
    return restTemplate.getForObject(
        finnhubQuoteUriBuilder.queryParam("symbol", symbol).toUriString(),
        Quote.class);
  }

  private CompanyProfile getProfileFromApi(String symbol) {
    log.info("Service " + finnhubCompanyProfileUriBuilder
        .queryParam("symbol", symbol).toUriString());
    return restTemplate.getForObject(finnhubCompanyProfileUriBuilder
        .queryParam("symbol", symbol).toUriString(), CompanyProfile.class);
  }

  @Override
  public StockDTO getStock(String symbol) {
    Quote quote = this.getQuote(symbol);
    CompanyProfile profile = this.getProfile(symbol);
    StockDTO stockDTO = mapper.map(quote, profile);
    return stockDTO;
  }
}
