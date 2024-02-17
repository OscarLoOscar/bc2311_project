package com.vtxlab.project.bc_product_quote.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StockDTO {
  private Integer code;
  private String message;
  private Data data;

  public class Data {
    public String name;
    public String ticker;
    @JsonProperty("Current_Price")
    public int currentPrice;
    @JsonProperty("Change")
    public int change;
    @JsonProperty("Percent_Change")
    public int percentChange;
    @JsonProperty("High_Price_Of_The_Day")
    public int highPriceOfTheDay;
    @JsonProperty("Low_Price_Of_The_Day")
    public int lowPriceOfTheDay;
    @JsonProperty("Open_Price_Of_The_Day")
    public int openPriceOfTheDay;
    @JsonProperty("Previous_Close_Price")
    public int previousClosePrice;
  }
}
