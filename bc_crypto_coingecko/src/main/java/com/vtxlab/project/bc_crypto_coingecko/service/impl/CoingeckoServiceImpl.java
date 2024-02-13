package com.vtxlab.project.bc_crypto_coingecko.service.impl;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.vtxlab.project.bc_crypto_coingecko.config.RedisUtils;
import com.vtxlab.project.bc_crypto_coingecko.entity.CoingeckoEntity;
import com.vtxlab.project.bc_crypto_coingecko.infra.Currency;
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

  // @Autowired
  // @Qualifier("coingeckoUriBuilder")
  // private UriComponentsBuilder uriComponentsBuilder;

  private String uri =
      "https://api.coingecko.com/api/v3/coins/markets?vs_currency=usd&order=market_cap_desc&per_page=100&page=1&sparkline=false&locale=en";

  @Override
  public List<Coingecko> getDataFromApi() {
    // log.info("Service : " + uriComponentsBuilder.toUriString());
    List<Coingecko> rawData =
        Arrays.stream(restTemplate.getForObject(uri, Coingecko[].class))
            .collect(Collectors.toList());
    for (Coingecko data : rawData) {
      CoingeckoEntity coingeckoEntity = mapper.mapToEntity(data);
      this.setDataToRedis(data);
      coingeckoRepo.save(coingeckoEntity);
    }

    return Arrays.stream(restTemplate.getForObject(uri, Coingecko[].class))
        .collect(Collectors.toList());
  }

  private boolean setDataToRedis(Coingecko data) { // btc , eth
    CoingeckoDTO coingeckoDTO = mapper.map(this.getDataFromApi().stream()//
        .filter(e -> Currency.BTC.name().equals(e.getId())
            || Currency.ETH.name().equals(e.getId()))//
        .findFirst()//
        .get());

    if (Currency.BTC.name().equals(coingeckoDTO.getId())) {
      redisUtils.set("BC2311_" + Currency.BTC.name(), coingeckoDTO);
      return true;
    } else {
      redisUtils.set("BC2311_" + Currency.ETH.name(), coingeckoDTO);
      return true;
    }
  }
}
