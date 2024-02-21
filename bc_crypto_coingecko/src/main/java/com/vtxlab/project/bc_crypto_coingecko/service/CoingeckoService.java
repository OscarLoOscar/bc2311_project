package com.vtxlab.project.bc_crypto_coingecko.service;

import java.util.List;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.vtxlab.project.bc_crypto_coingecko.model.Coingecko;
import com.vtxlab.project.bc_crypto_coingecko.model.CoingeckoDTO;

public interface CoingeckoService {

  List<Coingecko> getDataFromApi(String currency, String ids);

  List<CoingeckoDTO> getDataFromRedis(String symbol) throws JsonProcessingException;
}
