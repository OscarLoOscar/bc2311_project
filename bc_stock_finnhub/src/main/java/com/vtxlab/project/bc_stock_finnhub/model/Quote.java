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
  @JsonProperty("Current_Price")
  private Long c;
  @JsonProperty("Change")
  private Long d;
  @JsonProperty("Percent_Change")
  private Long dp;
  @JsonProperty("High_Price_Of_The_Day")
  private Long h;
  @JsonProperty("Low_Price_Of_The_Day")
  private Long l;
  @JsonProperty("Open_Price_Of_The_Day")
  private Long o;
  @JsonProperty("Previous_Close_Price")
  private Long pc;
  @JsonProperty("Timestamp")
  private Integer t;
}
