package com.vtxlab.project.bc_product_quote.config;

import java.util.List;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.util.UriComponentsBuilder;
import com.vtxlab.project.bc_product_quote.infra.ApiUtil;
import com.vtxlab.project.bc_product_quote.infra.UriScheme;

@Configuration
public class UriBuilderConfig {

  @Value("${api.bc_crypto_coingecko.port}")
  private int coingeckoPort;

  @Value("${api.bc_crypto_coingecko.domain}")
  private String coingeckoDomain;

  @Value("${api.bc_crypto_coingecko.path}")
  private String coingeckoPath;

  @Value("${api.bc_stock_finnhub.port}")
  private int finnhubPort;

  @Value("${api.bc_stock_finnhub.domain}")
  private String finnhubDomain;

  @Value("${api.bc_stock_finnhub.path}")
  private String finnhubPath;

  @Value("${api.bc_stock_finnhub.quote.endpoint}")
  private String finnhubQuoteEndpoint;

  @Value("${api.bc_stock_finnhub.profile.endpoint}")
  private String finnhubProfileEndpoint;

  @Value("${api.symbols}")
  private String symbolList;

  @Bean
  public String coingeckoUriString() {
    return coingeckoUriBuilder().toUriString();
  }

  @Bean
  public String finnhubQuoteUriString() {
    return finnhubQuoteUriBuilder().toUriString();
  }

  private UriComponentsBuilder coingeckoUriBuilder() {
    return createUriBuilder(coingeckoDomain, coingeckoPort, coingeckoPath);
  }

  private UriComponentsBuilder finnhubQuoteUriBuilder() {
    List<String> symbols = List.of(symbolList.split(","));
    return createUriBuilder(finnhubDomain, finnhubPort, finnhubPath,
        finnhubQuoteEndpoint, symbols);
  }

  private UriComponentsBuilder createUriBuilder(String domain, int port,
      String path) {
    UriComponentsBuilder builder =
        ApiUtil.uriBuilder(UriScheme.HTTP, domain, String.valueOf(port), path);
    return builder;
  }

  private UriComponentsBuilder createUriBuilder(String domain, int port,
      String path, String endpoint, List<String> symbols) {
    UriComponentsBuilder builder = ApiUtil.uriBuilder(UriScheme.HTTP, domain,
        String.valueOf(port), path, endpoint).queryParam("symbol", symbols);
    return builder;
  }
}
