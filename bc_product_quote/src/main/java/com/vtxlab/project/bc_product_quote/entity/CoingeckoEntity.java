package com.vtxlab.project.bc_product_quote.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
@Table(name = "texternal_crypto_coingecko_market", schema = "bc2311")
public class CoingeckoEntity implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

    private Timestamp quoteDate;
    private BigDecimal currPrice;
    private BigDecimal priceChg;
    private BigDecimal priceChgPot;
    private BigDecimal priceDayHigh;
    private BigDecimal priceDayLow;
    private BigDecimal pricePrevOpen;
    private BigDecimal pricePrevClose;
}
