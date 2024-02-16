package com.vtxlab.project.bc_stock_finnhub.exception;

import com.vtxlab.project.bc_stock_finnhub.exception.exceptionEnum.Code;
import lombok.Getter;

@Getter
public class BusinessException extends Exception {
  private Code code;

  public BusinessException(Code code) {
    super(code.getMessage());
    this.code = code;
  }
}
