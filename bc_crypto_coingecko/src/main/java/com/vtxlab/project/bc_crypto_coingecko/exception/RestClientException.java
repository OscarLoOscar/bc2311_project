package com.vtxlab.project.bc_crypto_coingecko.exception;

import com.vtxlab.project.bc_crypto_coingecko.exception.exceptionEnum.Code;

import lombok.Getter;

@Getter
public class RestClientException extends BusinessException {
  private Code code;

  public RestClientException(Code code) {
    super(code);
    this.code = code;
  }
}
