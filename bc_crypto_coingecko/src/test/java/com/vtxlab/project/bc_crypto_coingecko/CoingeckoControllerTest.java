package com.vtxlab.project.bc_crypto_coingecko;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.client.RestTemplate;

import com.vtxlab.project.bc_crypto_coingecko.config.RedisUtils;
import com.vtxlab.project.bc_crypto_coingecko.infra.Mapper;
import com.vtxlab.project.bc_crypto_coingecko.model.Coingecko;
import com.vtxlab.project.bc_crypto_coingecko.model.CoingeckoDTO;
import com.vtxlab.project.bc_crypto_coingecko.service.impl.CoingeckoServiceImpl;

public class CoingeckoControllerTest {
  @Mock
  private RestTemplate restTemplate;

  @Mock
  private RedisUtils redisUtils;

  @Mock
  private Mapper mapper;

  @InjectMocks
  private CoingeckoServiceImpl coingeckoService;

  @BeforeEach
  public void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  public void testGetDataFromApi() {
    String currency = "usd";
    String ids = "btc,eth";
    Coingecko[] coingecko = {new Coingecko(), new Coingecko()};
    when(restTemplate.getForObject(any(String.class), any()))
        .thenReturn(coingecko);

    List<CoingeckoDTO> coingeckoDTOList = new ArrayList<>();
    coingeckoDTOList.add(CoingeckoDTO.builder()//
        .id("btc")//
        .symbol("btc")//
        .name("Bitcoin")//
        .currentPrice(50000.0)//
        .marketCap(1000000000L)//
        .high24h(60000.0)//
        .low24h(40000.0)//
        .priceChange24h(1000.0)//
        .build());
    coingeckoDTOList.add(CoingeckoDTO.builder()//
        .id("eth")//
        .symbol("eth")//
        .name("Ethereum")//
        .currentPrice(3000.0)//
        .marketCap(500000000L)//
        .high24h(3500.0)//
        .low24h(2500.0)//
        .priceChange24h(200.0)//
        .build());
    when(mapper.map(any(Coingecko.class))).thenReturn(coingeckoDTOList.get(0),
        coingeckoDTOList.get(1));

    // Assertions
    // Verify that restTemplate.getForObject() is called with the correct URL
    verify(restTemplate).getForObject(
        "https://api.coingecko.com/api/v3/simple/price?ids=btc,eth&vs_currencies=usd",
        Coingecko[].class);

    // Verify that mapper.map() is called for each Coingecko object
    verify(mapper).map(coingecko[0]);
    verify(mapper).map(coingecko[1]);

    // Verify that redisUtils.set() is called with the correct key and value
    verify(redisUtils).set("btc", coingeckoDTOList.get(0));
    verify(redisUtils).set("eth", coingeckoDTOList.get(1));
  }
}
