package com.vtxlab.project.bc_product_quote.infra;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.vtxlab.project.bc_product_quote.entity.CoingeckoEntity;
import com.vtxlab.project.bc_product_quote.model.Coingecko;
import com.vtxlab.project.bc_product_quote.model.CoingeckoDTO;
import com.vtxlab.project.bc_product_quote.model.StockDTO;

@Component
public class Mapper {

  @Autowired
  ModelMapper modelMapper;

  public CoingeckoDTO mapToEntity(Coingecko data) {
    return CoingeckoDTO.builder().coinId(data.getId())//
        .symbol(data.getSymbol())//
        .name(data.getName())//
        .currentPrice(data.getCurrentPrice())//
        .marketCap(data.getMarketCap())//
        .high24h(data.getHigh24h())//
        .low24h(data.getLow24h())//
        .priceChange24h(data.getPriceChange24h())//
        .build();
  }

  public CoingeckoDTO map(CoingeckoEntity coingeckoEntity) {
    return CoingeckoDTO.builder()//
        .coinId(coingeckoEntity.getCoinId())//
        .symbol(coingeckoEntity.getSymbol())//
        .name(coingeckoEntity.getName())//
        .currentPrice(coingeckoEntity.getCurrentPrice())//
        .marketCap(coingeckoEntity.getMarketCap())//
        .high24h(coingeckoEntity.getHigh24h())//
        .low24h(coingeckoEntity.getLow24h())//
        .priceChange24h(coingeckoEntity.getPriceChange24h())//
        .build();
  }

}
