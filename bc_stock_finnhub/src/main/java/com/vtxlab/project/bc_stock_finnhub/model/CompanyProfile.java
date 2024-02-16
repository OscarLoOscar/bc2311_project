package com.vtxlab.project.bc_stock_finnhub.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CompanyProfile {
  private String country;
  private String currency;
  private String estimateCurrency;
  private String exchange;
  private String finnhubIndustry;
  private String ipo;
  private String logo;
  private Long marketCapitalization;
  private String name;
  private String phone;
  private Long shareOutstanding;
  private String ticker;
  private String weburl;
}
