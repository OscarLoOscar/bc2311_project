package com.vtxlab.project.bc_stock_finnhub.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import com.vtxlab.project.bc_stock_finnhub.infra.Mapper;
import com.vtxlab.project.bc_stock_finnhub.infra.RedisHelper;
import com.vtxlab.project.bc_stock_finnhub.model.CompanyProfile;
import com.vtxlab.project.bc_stock_finnhub.model.Quote;
import com.vtxlab.project.bc_stock_finnhub.model.StockDTO;
import com.vtxlab.project.bc_stock_finnhub.service.FinnhubService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class FinnhubServiceImpl implements FinnhubService {

  private static final int CACHE_EXPIRATION_TIME = 60;

  private final RestTemplate restTemplate;
  private final UriComponentsBuilder finnhubQuoteUriBuilder;
  private final UriComponentsBuilder finnhubCompanyProfileUriBuilder;
  private final RedisHelper redisHelper;
  private final Mapper mapper;

  @Autowired
  public FinnhubServiceImpl(RestTemplate restTemplate,
      @Qualifier("finnhubQuoteUriBuilder") UriComponentsBuilder finnhubQuoteUriBuilder,
      @Qualifier("finnhubCompanyProfileUriBuilder") UriComponentsBuilder finnhubCompanyProfileUriBuilder,
      RedisHelper redisHelper, Mapper mapper) {
    this.restTemplate = restTemplate;
    this.finnhubQuoteUriBuilder = finnhubQuoteUriBuilder;
    this.finnhubCompanyProfileUriBuilder = finnhubCompanyProfileUriBuilder;
    this.redisHelper = redisHelper;
    this.mapper = mapper;
  }

  @Override
  public Quote getQuote(String symbol) {
    Quote quote = getQuoteFromApi(symbol);
    return quote;
  }

  @Override
  public CompanyProfile getProfile(String symbol) {
    CompanyProfile companyProfile = getProfileFromApi(symbol);
    return companyProfile;
  }

  private Quote getQuoteFromApi(String symbol) {
    UriComponentsBuilder builder = UriComponentsBuilder
        .fromUriString(finnhubQuoteUriBuilder.toUriString());
    builder.replaceQueryParam("symbol", symbol);
    log.info("getQuoteFromApi Service :" + builder.toUriString());
    return restTemplate.getForObject(builder.toUriString(), Quote.class);
  }

  private CompanyProfile getProfileFromApi(String symbol) {
    UriComponentsBuilder builder = UriComponentsBuilder
        .fromUriString(finnhubCompanyProfileUriBuilder.toUriString());
    builder.replaceQueryParam("symbol", symbol);
    log.info("getProfileFromApi Service :" + builder.toUriString());
    return restTemplate.getForObject(builder.toUriString(),
        CompanyProfile.class);
  }

  @Override
  public StockDTO getStock(String symbol) {
    Quote quote = this.getQuote(symbol);
    CompanyProfile profile = this.getProfile(symbol);
    return mapper.map(quote, profile);
  }
}
