package com.vtxlab.project.bc_crypto_coingecko.controller.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.vtxlab.project.bc_crypto_coingecko.controller.CoingeckoOperation;
import com.vtxlab.project.bc_crypto_coingecko.exception.ApiResp;
import com.vtxlab.project.bc_crypto_coingecko.exception.exceptionEnum.Code;
import com.vtxlab.project.bc_crypto_coingecko.model.Coingecko;
import com.vtxlab.project.bc_crypto_coingecko.service.CoingeckoService;

@RestController
@RequestMapping("/api/v1/coingecko")
public class CoingeckoController implements CoingeckoOperation {

  @Autowired
  private CoingeckoService coingeckoService;

  @Override
  public ApiResp<List<Coingecko>> getAllData() {
    List<Coingecko> data = coingeckoService.getDataFromApi();
    return ApiResp.<List<Coingecko>>builder()//
        .code(Code.OK.getCode())//
        .message(Code.OK.getMessage())//
        .data(data)//
        .build();//
  }

}
