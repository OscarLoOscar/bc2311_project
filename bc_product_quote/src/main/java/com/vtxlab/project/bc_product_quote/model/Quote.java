package com.vtxlab.project.bc_product_quote.model;

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
public class Quote {
  private Integer Syscode;
  private String message;
  private Data data;

  @NoArgsConstructor
  @AllArgsConstructor
  @Getter
  @Builder
  public static class Data {
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
  }
}
