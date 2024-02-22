package com.vtxlab.project.bc_crypto_coingecko.config;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import com.vtxlab.project.bc_crypto_coingecko.infra.Mapper;
import com.vtxlab.project.bc_crypto_coingecko.model.Coingecko;
import com.vtxlab.project.bc_crypto_coingecko.model.CoingeckoDTO;
import com.vtxlab.project.bc_crypto_coingecko.service.CoingeckoService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@Profile("!test") // 表示这个配置或者方法只在非测试环境下生效。也就是说，当你的Spring环境不是"test"时，这个配置或者方法才会被加载和执行。
public class AppRunner implements CommandLineRunner {

  // ready the element to start the sever
  // main testk in AppRunner:
  // 1.Check on configuration - DB or yml
  // 2.Invoke API to get data(i.e. configuration , raw data)

  @Autowired
  CoingeckoService coingeckoService;

  @Autowired
  RedisHelper redisHelper;

  @Autowired
  Mapper mapper;

  @Value("${redis-key.crypto.coingecko.coins-markets.currency}")
  String currency;

  @Value("${redis-key.crypto.coingecko.coins-markets.coin-ids}")
  String coinIds;

  private void setDataToRedis(List<CoingeckoDTO> entities) {
    entities.forEach(e -> {
      redisHelper.set(
          "crypto:coingecko:coins-markets:" + currency + ":" + e.getId(), e,
          60);
    });
  }

  @Override
  public void run(String... args) throws Exception {
    List<Coingecko> targetData = coingeckoService.getCoinMarket();

    List<String> validCoinList = Arrays.asList(coinIds.split(","));
    log.info("validCoinList: {}", validCoinList);
    validCoinList.stream()//
        .forEach(coinId -> {
          List<CoingeckoDTO> result = targetData.stream()//
              .filter(e -> validCoinList.contains(e.getSymbol()))
              .map(e -> mapper.map(e))//
              .collect(Collectors.toList());
          this.setDataToRedis(result);
        });
  };

}

// @Override
// public List<CoingeckoDTO> getDataFromRedis(String symbol) {
// List<CoingeckoDTO> result = new ArrayList<>();
// String key = "crypto:coingecko:coins-markets:" + currency.toUpperCase()
// + ":" + symbol;
// String json = (String) redisUtils.get(key);
// if (json != null) {
// try {
// CoingeckoDTO target = objectMapper.readValue(json, CoingeckoDTO.class);
// result.add(target);
// } catch (JsonProcessingException e) {
// log.error("Error deserializing JSON to CoingeckoDTO object: {}",
// e.getMessage());
// }
// }
// return result;
// }

// }
// }
