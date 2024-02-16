package com.vtxlab.project.bc_stock_finnhub.exception;

import com.vtxlab.project.bc_stock_finnhub.exception.exceptionEnum.Code;
import lombok.Getter;

@Getter
public class BusinessRuntimeException extends RuntimeException {
  private Code code;

  public BusinessRuntimeException(Code code) {
    super(code.getMessage());
    this.code = code;
  }

}
