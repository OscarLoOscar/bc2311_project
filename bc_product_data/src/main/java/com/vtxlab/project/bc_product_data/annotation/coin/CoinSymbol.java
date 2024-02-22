package com.vtxlab.project.bc_product_data.annotation.coin;

import java.util.Arrays;
import java.util.List;
import org.springframework.beans.factory.annotation.Value;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CoinSymbol {
  @Value("${api.bc_crypto_coingecko.ids}")
  private String coinSymbol;

  public List<String> getCoinIds() {
    return Arrays.asList(coinSymbol.split(","));
  }

}
