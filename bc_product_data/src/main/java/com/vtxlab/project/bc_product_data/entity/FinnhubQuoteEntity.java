package com.vtxlab.project.bc_product_data.entity;

import java.math.BigDecimal;
import java.sql.Timestamp;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "texternal_stock_finnhub_quote")
public class FinnhubQuoteEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  private Timestamp quoteDate;
  private String stockCode;
  private BigDecimal currPrice;
  private BigDecimal priceChg;
  private BigDecimal priceChgPot;
  private BigDecimal priceDayHigh;
  private BigDecimal priceDayLow;
  private BigDecimal pricePrevOpen;
  private BigDecimal pricePrevClose;
}
