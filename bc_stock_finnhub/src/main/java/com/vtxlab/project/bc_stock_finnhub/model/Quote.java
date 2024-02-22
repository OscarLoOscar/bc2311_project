package com.vtxlab.project.bc_stock_finnhub.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Quote {
  // @JsonProperty("Current_Price")
  private Double c;
  // @JsonProperty("Change")
  private Double d;
  // @JsonProperty("Percent_Change")
  private Double dp;
  // @JsonProperty("High_Price_Of_The_Day")
  private Double h;
  // @JsonProperty("Low_Price_Of_The_Day")
  private Double l;
  // @JsonProperty("Open_Price_Of_The_Day")
  private Double o;
  // @JsonProperty("Previous_Close_Price")
  private Double pc;
  // @JsonProperty("Timestamp")
  private Long t;
}
