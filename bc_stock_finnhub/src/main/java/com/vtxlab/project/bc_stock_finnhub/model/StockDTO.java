package com.vtxlab.project.bc_stock_finnhub.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class StockDTO {
  private String name;

  private String ticker;

  @JsonProperty("Current_Price")
  private Long currentPrice;
  @JsonProperty("Change")
  private Long change;
  @JsonProperty("Percent_Change")
  private Long percentChange;
  @JsonProperty("High_Price_Of_The_Day")
  private Long highPrice;
  @JsonProperty("Low_Price_Of_The_Day")
  private Long lowPrice;
  @JsonProperty("Open_Price_Of_The_Day")
  private Long openPrice;
  @JsonProperty("Previous_Close_Price")
  private Long closePrice;

}
