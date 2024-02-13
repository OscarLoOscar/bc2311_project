package com.vtxlab.project.bc_crypto_coingecko.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
public class CoingeckoEntity implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String coinId;// rawData id
  private String symbol;
  private String name;
  private String image;

  @JsonProperty("current_price")
  private double currentPrice;

  @JsonProperty("cap")
  private long marketCap;

  @JsonProperty("market_cap_rank")
  private long marketCapRank;

  @JsonProperty("fully_diluted_valuation")
  private long fullyDilutedValuation;

  @JsonProperty("total_volume")
  private long totalVolume;

  @JsonProperty("high_24h")
  private double high24h;

  @JsonProperty("low_24h")
  private double low24h;

  @JsonProperty("price_change_24h")
  private double priceChange24h;

  @JsonProperty("price_change_percentage_24h")
  private double priceChangePercentage24h;

  @JsonProperty("market_cap_change_24h")
  private long marketCapChange24h;
  @JsonProperty("market_cap_change_percentage_24h")
  private double marketCapChangePercentage24h;
  @JsonProperty("circulating_supply")
  private long circulatingSupply;

  @JsonProperty("total_supply")
  private long totalSupply;
  @JsonProperty("max_supply")
  private long maxSupply;

  private long ath;
  @JsonProperty("ath_change_percentage")
  private double athChangePercentage;

  @JsonProperty("ath_date")
  // @JsonDeserialize(using = LocalDateTimeDeserializer.class)
  private LocalDateTime athDate;
  private double atl;
  @JsonProperty("atl_change_percentage")
  private double atlChangePercentage;
  @JsonProperty("atl_date")
  private LocalDateTime atlDate;
  @JsonProperty("last_updated")
  private LocalDateTime lastUpdated;

  private double times;
  private String currency;
  private double percentage;

}
