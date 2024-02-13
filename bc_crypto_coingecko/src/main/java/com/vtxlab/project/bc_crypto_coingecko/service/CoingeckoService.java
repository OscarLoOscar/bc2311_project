package com.vtxlab.project.bc_crypto_coingecko.service;

import java.util.List;
import com.vtxlab.project.bc_crypto_coingecko.model.Coingecko;

public interface CoingeckoService {

  List<Coingecko> getDataFromApi();
}
