package com.vtxlab.project.bc_product_data.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.util.UriComponentsBuilder;
import com.vtxlab.project.bc_product_data.infra.ApiUtil;
import com.vtxlab.project.bc_product_data.infra.UriScheme;
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

  @Value("${api.bc_stock_finnhub.endpoint.quote}")
  private String quoteEndpoint;

  @Value("${api.bc_stock_finnhub.endpoint.profile}")
  private String profileEndpoint;

  @Value("${api.bc_stock_finnhub.endpoint.stock}")
  private String stockEndpoint;

  @Value("${api.bc_stock_finnhub.symbols}")
  private String symbolList;

  @Bean
  public UriComponentsBuilder coingeckoUriString() {
    return createUriBuilder(coingeckoDomain, coingeckoCurrency, coingeckoIds);
  }

  @Bean
  public UriComponentsBuilder finnhubStockUriString() {
    return createUriBuilder(finnhubDomain, stockEndpoint);
  }

  @Bean
  public UriComponentsBuilder finnhubQuoteUriString() {
    return createUriBuilder(finnhubDomain, quoteEndpoint);
  }

  @Bean
  public UriComponentsBuilder finnhubProfileUriString() {
    return createUriBuilder(finnhubDomain, profileEndpoint);
  }

  private UriComponentsBuilder createUriBuilder(String domain,
      String... endpoints) {
    UriComponentsBuilder builder =
        ApiUtil.uriBuilder(UriScheme.HTTP, domain, endpoints);
    log.info("UriBuilder: " + builder.toUriString());
    return builder;
  }
}
