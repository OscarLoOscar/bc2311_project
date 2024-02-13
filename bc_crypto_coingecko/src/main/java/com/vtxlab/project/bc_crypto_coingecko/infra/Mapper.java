package com.vtxlab.project.bc_crypto_coingecko.infra;

import org.springframework.stereotype.Component;
import com.vtxlab.project.bc_crypto_coingecko.entity.CoingeckoEntity;
import com.vtxlab.project.bc_crypto_coingecko.model.Coingecko;
import com.vtxlab.project.bc_crypto_coingecko.model.CoingeckoDTO;

@Component
public class Mapper {

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
    return CoingeckoEntity.builder()//
        .coinId(data.getId())//
        .symbol(data.getSymbol())//
        .name(data.getName())//
        .image(data.getImage())//
        .currentPrice(data.getCurrentPrice())//
        .marketCap(data.getMarketCap())//
        .marketCapRank(data.getMarketCapRank())//
        .totalVolume(data.getTotalVolume())//
        .high24h(data.getHigh24h())//
        .low24h(data.getLow24h())//
        .priceChange24h(data.getPriceChange24h())//
        .priceChangePercentage24h(data.getPriceChangePercentage24h())//
        .marketCapChange24h(data.getMarketCapChange24h())//
        .marketCapChangePercentage24h(data.getMarketCapChangePercentage24h())//
        .circulatingSupply(data.getCirculatingSupply())//
        .totalSupply(data.getTotalSupply())//
        .ath(data.getAth())//
        .athChangePercentage(data.getAthChangePercentage())//
        .athDate(data.getAthDate())//
        // .times(data.getRoi().getTimes())//
        // .currency(data.getRoi().getCurrency())//
        // .percentage(data.getRoi().getPercentage())//
        .lastUpdated(data.getLastUpdated())//
        .build();
  }
}
