package com.vtxlab.project.bc_crypto_coingecko.service.impl;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import com.vtxlab.project.bc_crypto_coingecko.config.RedisUtils;
import com.vtxlab.project.bc_crypto_coingecko.entity.CoingeckoEntity;
import com.vtxlab.project.bc_crypto_coingecko.infra.TraditionCurrency;
import com.vtxlab.project.bc_crypto_coingecko.infra.CryptoCurrency;
import com.vtxlab.project.bc_crypto_coingecko.infra.Mapper;
import com.vtxlab.project.bc_crypto_coingecko.model.Coingecko;
import com.vtxlab.project.bc_crypto_coingecko.model.CoingeckoDTO;
import com.vtxlab.project.bc_crypto_coingecko.repository.CoingeckoRepo;
import com.vtxlab.project.bc_crypto_coingecko.service.CoingeckoService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class CoingeckoServiceImpl implements CoingeckoService {

  @Autowired
  private RestTemplate restTemplate;

  @Autowired
  private CoingeckoRepo coingeckoRepo;

  @Autowired
  private RedisUtils redisUtils;

  @Autowired
  private Mapper mapper;

  @Autowired
  @Qualifier("coingeckoUriBuilder")
  private UriComponentsBuilder coingeckoUriBuilder;

  @Override
  public List<Coingecko> getDataFromApi() {
    List<Coingecko> rawData = this.getCoinMarket();

    List<CoingeckoEntity> entities = rawData.stream()
        .filter(coin -> Arrays.stream(CryptoCurrency.values())
            .anyMatch(e -> e.name().toLowerCase().equals(coin.getSymbol())))//
        .map(e -> mapper.mapToEntity(e))//
        .collect(Collectors.toList());

    log.info("entities : " + entities.size());
    coingeckoRepo.saveAll(entities);
    log.info("After saveAll");
    this.setDataToRedis(entities);
    log.info("After save to redis");

    return rawData;
  }

  private List<Coingecko> getCoinMarket() {
    return Arrays.asList(restTemplate
        .getForObject(coingeckoUriBuilder.toUriString(), Coingecko[].class));
  }

  private void setDataToRedis(List<CoingeckoEntity> entities) {
    List<CoingeckoDTO> coingeckoDTOs =
        entities.stream().map(e -> mapper.map(e)).collect(Collectors.toList());

    coingeckoDTOs.forEach(coin -> {
      redisUtils.set("BC2311_" + coin.getSymbol(), coin);
    });
  }
}
