package com.vtxlab.project.bc_crypto_coingecko.controller.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.vtxlab.project.bc_crypto_coingecko.controller.CoingeckoOperation;
import com.vtxlab.project.bc_crypto_coingecko.exception.ApiResp;
import com.vtxlab.project.bc_crypto_coingecko.exception.exceptionEnum.Syscode;
import com.vtxlab.project.bc_crypto_coingecko.model.Coingecko;
import com.vtxlab.project.bc_crypto_coingecko.service.CoingeckoService;


@RestController
@RequestMapping("/crypto/coingecko/api/v1")
public class CoingeckoController implements CoingeckoOperation {

  @Autowired
  private CoingeckoService coingeckoService;

  @Override
  public ApiResp<List<Coingecko>> getAllData(String currency, String ids) {
    List<Coingecko> data = coingeckoService.getDataFromApi(currency, ids);
    return ApiResp.<List<Coingecko>>builder()//
        .Syscode(Syscode.OK.getSyscode())//
        .message(Syscode.OK.getMessage())//
        .data(data)//
        .build();//
  }

  @Override
  public ApiResp<List<String>> getCoinList() {
    return ApiResp.<List<String>>builder()//
        .Syscode(Syscode.OK.getSyscode())//
        .message(Syscode.OK.getMessage())//
        .data(coingeckoService.getCoinList())//
        .build();//
  }
}
