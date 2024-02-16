package com.vtxlab.project.bc_product_quote.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CoingeckoDTO {
  private String coinId;
  private String symbol;
  private String name;
  private double currentPrice;
  private long marketCap;
  private double high24h;
  private double low24h;
  private double priceChange24h;
}
