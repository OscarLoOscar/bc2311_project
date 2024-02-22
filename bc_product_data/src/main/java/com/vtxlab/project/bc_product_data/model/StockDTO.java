package com.vtxlab.project.bc_product_data.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StockDTO {
  private Integer Syscode;
  private String message;
  private Data data;

  @NoArgsConstructor
  @AllArgsConstructor
  @Getter
  @Builder
  public static class Data {
    private String name;
    private String ticker;
    @JsonProperty("Current_Price")
    private int currentPrice;
    @JsonProperty("Change")
    private int change;
    @JsonProperty("Percent_Change")
    private int percentChange;
    @JsonProperty("High_Price_Of_The_Day")
    private int highPriceOfTheDay;
    @JsonProperty("Low_Price_Of_The_Day")
    private int lowPriceOfTheDay;
    @JsonProperty("Open_Price_Of_The_Day")
    private int openPriceOfTheDay;
    @JsonProperty("Previous_Close_Price")
    private int previousClosePrice;
  }
}
