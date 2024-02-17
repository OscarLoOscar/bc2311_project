package com.vtxlab.project.bc_product_quote.config;

import java.util.List;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.util.UriComponentsBuilder;
import com.vtxlab.project.bc_product_quote.infra.ApiUtil;
import com.vtxlab.project.bc_product_quote.infra.UriScheme;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
public class UriBuilderConfig {


  @Value("${api.bc_crypto_coingecko.domain}")
  private String coingeckoDomain;

  @Value("${api.bc_crypto_coingecko.endpoints.currency}")
  private String coingeckoCurrency;

  @Value("${api.bc_crypto_coingecko.endpoints.ids}")
  private String coingeckoIds;

  @Value("${api.bc_stock_finnhub.domain}")
  private String finnhubDomain;

  @Value("${api.bc_stock_finnhub.symbols}")
  private String symbolList;

  @Bean
  public UriComponentsBuilder coingeckoUriString() {
    return coingeckoUriBuilder();
  }

  @Bean
  public UriComponentsBuilder finnhubStockUriString() {
    return finnhubStockUriBuilder();
  }

  private UriComponentsBuilder coingeckoUriBuilder() {
    log.info("coingeckoDomain : "+coingeckoDomain);
    return createCoingeckoUriBuilder(coingeckoDomain);
  }

  private UriComponentsBuilder finnhubStockUriBuilder() {
    log.info("finnhubDomain : " +finnhubDomain);
    return createFinnhubUriBuilder(finnhubDomain);
  }

  private UriComponentsBuilder createCoingeckoUriBuilder(String domain) {
    UriComponentsBuilder builder = ApiUtil.uriBuilder(UriScheme.HTTP, domain);
    builder.queryParam("currency", coingeckoCurrency);
    builder.queryParam("ids", coingeckoIds);
    log.info("CoingeckoUriBuilder : " + builder.toUriString());
    return builder;
  }

  private UriComponentsBuilder createFinnhubUriBuilder(String domain) {
    UriComponentsBuilder builder = ApiUtil.uriBuilder(UriScheme.HTTP, domain);
    log.info("finnhubQuoteUriBuilder : " + builder.toUriString());
    return builder;
  }
}
