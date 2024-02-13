package com.vtxlab.project.bc_crypto_coingecko.exception;

import com.vtxlab.project.bc_crypto_coingecko.exception.exceptionEnum.Code;
import lombok.Getter;

@Getter
public class BusinessException extends Exception {
  private Code code;

  public BusinessException(Code code) {
    super(code.getMessage());
    this.code = code;
  }
}
