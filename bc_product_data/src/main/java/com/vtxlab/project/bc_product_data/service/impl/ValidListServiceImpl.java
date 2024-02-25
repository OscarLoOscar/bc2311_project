package com.vtxlab.project.bc_product_data.service.impl;

import java.util.Arrays;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import com.vtxlab.project.bc_product_data.entity.CoinList;
import com.vtxlab.project.bc_product_data.entity.StockList;
import com.vtxlab.project.bc_product_data.model.request.CoinListRequestDTO;
import com.vtxlab.project.bc_product_data.model.request.StockListRequestDTO;
import com.vtxlab.project.bc_product_data.model.response.CoinListResponseDTO;
import com.vtxlab.project.bc_product_data.model.response.StockListResponseDTO;
import com.vtxlab.project.bc_product_data.repository.CoinListRepo;
import com.vtxlab.project.bc_product_data.repository.StockListRepo;
import com.vtxlab.project.bc_product_data.service.ValidListService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ValidListServiceImpl implements ValidListService {

  @Autowired
  private StockListRepo stockListRepo;

  @Autowired
  private CoinListRepo coinListRepo;

  @Autowired
  private RestTemplate restTemplate;

  @Autowired
  @Qualifier("coingeckoUriString")
  private UriComponentsBuilder coingeckoUriString;

  @Autowired
  @Qualifier("finnhubStockUriString")
  private UriComponentsBuilder finnhubStockUriString;

  @Override
  public boolean saveCoinList() {
    log.info("check coin: " + coingeckoUriString.build(false).toUriString());
    CoinListRequestDTO rawData =
        restTemplate.getForObject(coingeckoUriString.build(false).toUriString(),
            CoinListRequestDTO.class);
    rawData.getData().stream()//
        .map(e -> CoinList.builder().coinId(e).build())//
        .forEach(coinListRepo::save);
    return true;
  }

  @Override
  public boolean saveStockList() {
    log.info("check stock: " + finnhubStockUriString.build(false).toUriString());

    StockListRequestDTO rawData = restTemplate.getForObject(
        finnhubStockUriString.build(false).toUriString(), StockListRequestDTO.class);
    rawData.getData().stream()//
        .map(e -> StockList.builder().stockId(e).build())//
        .forEach(stockListRepo::save);
    return true;
  }

  @Override
  public boolean deleteCoin(String coinId) {
    coinListRepo.deleteByCoinId(coinId);
    return true;
  }

  @Override
  public boolean deleteStock(String stockId) {
    stockListRepo.deleteByStockId(stockId);
    return true;
  }

  @Override
  public CoinListResponseDTO getCoinList() {
    List<String> data = coinListRepo.findAll().stream()//
        .map(coinList -> coinList.getCoinId())//
        .toList();
    return CoinListResponseDTO.builder()//
        .coinList(data)//
        .build();
  }

  @Override
  public StockListResponseDTO getStockList() {
    List<String> data = stockListRepo.findAll().stream()//
        .map(stockList -> stockList.getStockId())//
        .toList();
    return StockListResponseDTO.builder()//
        .stockList(data)//
        .build();
  }

  @Override
  public boolean deleteAll() {
    coinListRepo.deleteAll();
    stockListRepo.deleteAll();
    return true;
  }

}
