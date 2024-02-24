package com.vtxlab.project.bc_crypto_coingecko.controller;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import com.vtxlab.project.bc_crypto_coingecko.exception.ApiResp;
import com.vtxlab.project.bc_crypto_coingecko.model.Coingecko;

public interface CoingeckoOperation {

  @GetMapping("")
  @ResponseStatus(HttpStatus.OK)
  public ApiResp<List<Coingecko>> getAllData(@RequestParam String currency, @RequestParam String ids);

  @GetMapping("/coin-list")
  @ResponseStatus(HttpStatus.OK)
  public ApiResp<List<String>> getCoinList();
}
