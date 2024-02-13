package com.vtxlab.project.bc_crypto_coingecko.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CoingeckoDTO {

  private String id;
  private String symbol;
  private String name;
  private String image;

  @JsonProperty("current_price")
  private double currentPrice;

  @JsonProperty("cap")
  private long marketCap;

  @JsonProperty("high_24h")
  private double high24h;

  @JsonProperty("low_24h")
  private double low24h;

  @JsonProperty("price_change_24h")
  private double priceChange24h;

}
