package com.vtxlab.project.bc_stock_finnhub.exception.exceptionEnum;

import lombok.Getter;

@Getter
public enum Code {
  OK(000000, "OK"), //
  INVALID_INPUT(9, "Invalid input"), //
  INVALID_OPERATION(10, "Invalid operation"), //
  // Api error
  API_ERROR(100, "API error"), //
  COINGECKO_SERVICE_UNAVAILABLE(900000, "Coingecko service is unavailable");
  //

  private int code;
  private String message;

  private Code(int code, String message) {
    this.code = code;
    this.message = message;
  }

  public static Code fromCode(int code) {
    for (Code c : Code.values()) {
      if (c.getCode() == code) {
        return c;
      }
    }
    return null;
  }

}
