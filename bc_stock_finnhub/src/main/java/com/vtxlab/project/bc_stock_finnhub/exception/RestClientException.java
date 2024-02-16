package com.vtxlab.project.bc_stock_finnhub.exception;

import com.vtxlab.project.bc_stock_finnhub.exception.exceptionEnum.Code;
import lombok.Getter;

@Getter
public class RestClientException extends BusinessException {
  private Code code;

  public RestClientException(Code code) {
    super(code);
    this.code = code;
  }
}
