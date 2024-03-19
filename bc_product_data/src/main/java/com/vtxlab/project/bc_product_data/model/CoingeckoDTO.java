package com.vtxlab.project.bc_product_data.model;

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
  @JsonProperty("total_volume")
  private long totalVolume;
  @JsonProperty("current_price")
  private double currentPrice;
  @JsonProperty("market_cap")
  private long marketCap;
  @JsonProperty("market_cap_rank")
  private long marketCapRank;
  @JsonProperty("price_change_percentage_24h")
  private double priceChangePercentage24h;
}
