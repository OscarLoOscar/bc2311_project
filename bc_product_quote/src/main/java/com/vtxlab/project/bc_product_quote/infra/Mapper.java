package com.vtxlab.project.bc_product_quote.infra;

import java.util.ArrayList;
import java.util.List;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.vtxlab.project.bc_product_quote.entity.CoingeckoEntity;
import com.vtxlab.project.bc_product_quote.model.Coingecko;
import com.vtxlab.project.bc_product_quote.model.CoingeckoDTO;

@Component
public class Mapper {

  @Autowired
  ModelMapper modelMapper;

  public List<CoingeckoDTO> map(List<Coingecko.Data> dataList) {
    List<CoingeckoDTO> coingeckoDTOList = new ArrayList<>();

    for (Coingecko.Data data : dataList) {
      CoingeckoDTO coingeckoDTO = CoingeckoDTO.builder()//
          .coinId(data.getId())//
          .symbol(data.getSymbol())//
          .name(data.getName())//
          .currentPrice(data.getCurrentPrice())//
          .marketCap(data.getMarketCap())//
          .high24h(data.getHigh24h())//
          .low24h(data.getLow24h())//
          .priceChange24h(data.getPriceChange24h())//
          .build();

      coingeckoDTOList.add(coingeckoDTO);
    }

    return coingeckoDTOList;
  }

  // public CoingeckoDTO map(CoingeckoEntity coingeckoEntity) {
  //   return CoingeckoDTO.builder().coinId(coingeckoEntity.getCoinId())
  //       .symbol(coingeckoEntity.getSymbol()).name(coingeckoEntity.getName())
  //       .currentPrice(coingeckoEntity.getCurrPrice().doubleValue())
  //       .marketCap(coingeckoEntity.getMarketCap().longValue())
  //       .high24h(coingeckoEntity.getHigh24h().doubleValue())
  //       .low24h(coingeckoEntity.getLow24h().doubleValue())
  //       .priceChange24h(coingeckoEntity.getPriceChange24h().doubleValue())
  //       .build();
  // }
}

