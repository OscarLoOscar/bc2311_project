package com.vtxlab.project.bc_crypto_coingecko.infra;

import lombok.Getter;

@Getter
public enum Currency {
  // Tradition Currecy
  HKD("T"), //
  USD("T"),
  // Crypto Currecy
  BTC("C"), //
  ETH("C"),//
  ;

  private String type;

  private Currency(String type) {
    this.type = type;
  }

  public boolean isCrypto() {
    return "C".equals(type);
  }
}
