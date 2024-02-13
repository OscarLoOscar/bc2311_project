package com.vtxlab.project.bc_crypto_coingecko.controller;

import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import com.vtxlab.project.bc_crypto_coingecko.exception.ApiResp;
import com.vtxlab.project.bc_crypto_coingecko.model.Coingecko;

public interface CoingeckoOperation {

  @GetMapping("/")
  public ApiResp<List<Coingecko>> getAllData();

}
