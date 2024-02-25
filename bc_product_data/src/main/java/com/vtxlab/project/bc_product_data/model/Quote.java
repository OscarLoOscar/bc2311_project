package com.vtxlab.project.bc_product_data.model;

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
  private String syscode;
  private String message;
  private Data data;

  @NoArgsConstructor
  @AllArgsConstructor
  @Getter
  @Builder
  public static class Data {
    private Double c;
    private Double d;
    private Double dp;
    private Double h;
    private Double l;
    private Double o;
    private Double pc;
    private Long t;
  }
}
