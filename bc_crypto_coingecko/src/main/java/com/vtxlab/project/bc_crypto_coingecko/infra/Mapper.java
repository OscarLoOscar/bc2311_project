package com.vtxlab.project.bc_crypto_coingecko.infra;

import java.util.Optional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.vtxlab.project.bc_crypto_coingecko.entity.CoingeckoEntity;
import com.vtxlab.project.bc_crypto_coingecko.model.Coingecko;
import com.vtxlab.project.bc_crypto_coingecko.model.CoingeckoDTO;

@Component
public class Mapper {

  @Autowired
  ModelMapper modelMapper;

  public CoingeckoDTO map(Coingecko coingecko) {
    return CoingeckoDTO.builder()//
        .id(coingecko.getId())//
        .symbol(coingecko.getSymbol())//
        .name(coingecko.getName())//
        .image(coingecko.getImage())//
        .currentPrice(coingecko.getCurrentPrice())//
        .marketCap(coingecko.getMarketCap())//
        .high24h(coingecko.getHigh24h())//
        .low24h(coingecko.getLow24h())//
        .priceChange24h(coingecko.getPriceChange24h())//
        .build();
  }

  public CoingeckoEntity mapToEntity(Coingecko data) {
    CoingeckoEntity.CoingeckoEntityBuilder builder = CoingeckoEntity.builder()
        .coinId(data.getId())
        .symbol(data.getSymbol())
        .name(data.getName())
        .image(data.getImage())
        .currentPrice(data.getCurrentPrice())
        .marketCap(data.getMarketCap())
        .marketCapRank(data.getMarketCapRank())
        .totalVolume(data.getTotalVolume())
        .high24h(data.getHigh24h())
        .low24h(data.getLow24h())
        .priceChange24h(data.getPriceChange24h())
        .priceChangePercentage24h(data.getPriceChangePercentage24h())
        .marketCapChange24h(data.getMarketCapChange24h())
        .marketCapChangePercentage24h(data.getMarketCapChangePercentage24h())
        .circulatingSupply(data.getCirculatingSupply())
        .totalSupply(data.getTotalSupply())
        .ath(data.getAth())
        .athChangePercentage(data.getAthChangePercentage())
        .athDate(data.getAthDate())
        .lastUpdated(data.getLastUpdated());

    if (data.getRoi() != null) {
      builder.times(data.getRoi().getTimes())
          .currency(data.getRoi().getCurrency())
          .percentage(data.getRoi().getPercentage());
    }

    return builder.build();
  }

  public CoingeckoDTO map(CoingeckoEntity coingeckoEntity) {
    return CoingeckoDTO.builder()//
        .id(coingeckoEntity.getCoinId())//
        .symbol(coingeckoEntity.getSymbol())//
        .name(coingeckoEntity.getName())//
        .image(coingeckoEntity.getImage())//
        .currentPrice(coingeckoEntity.getCurrentPrice())//
        .marketCap(coingeckoEntity.getMarketCap())//
        .high24h(coingeckoEntity.getHigh24h())//
        .low24h(coingeckoEntity.getLow24h())//
        .priceChange24h(coingeckoEntity.getPriceChange24h())//
        .build();
  }
}
