package com.vtxlab.project.bc_crypto_coingecko.exception.handler;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import com.vtxlab.project.bc_crypto_coingecko.exception.ApiResp;
import com.vtxlab.project.bc_crypto_coingecko.exception.exceptionEnum.Code;

@RestControllerAdvice
public class GlobalExceptionHandler {

  // @ExceptionHandler(value = BusinessException.class)
  // @ResponseStatus(value = HttpStatus.BAD_REQUEST)
  // public ApiResp<Void> bc2311ExceptionHandler(BusinessException e) {
  // return ApiResp.<Void>builder() //
  // .status(Code.fromCode(e.getCode().getCode())) //
  // .concatMessageIfPresent(e.getMessage())//
  // // .data(null) //
  // .build();
  // }

  // @ExceptionHandler(value = RuntimeException.class)
  // @ResponseStatus(value = HttpStatus.BAD_REQUEST)
  // public ApiResp<Void> runtimeExceptionHandler(RuntimeException e) {
  // return ApiResp.<Void>builder() //
  // .status(getRespCode(e)) //
  // .concatMessageIfPresent(e.getMessage())//
  // // .data(null) //
  // .build();
  // }

  // @ExceptionHandler(value = Exception.class)
  // @ResponseStatus(value = HttpStatus.BAD_REQUEST)
  // public ApiResp<Void> exceptionHandler(Exception e) {
  // return ApiResp.<Void>builder() //
  // .status(getRespCode(e)) //
  // .concatMessageIfPresent(e.getMessage())//
  // // .data(null) //
  // .build();
  // }

  private static Code getRespCode(Exception e) {
  
    if (e instanceof IllegalArgumentException) {
      return Code.INVALID_INPUT;
    }
    // ...
    // return null;
    return Code.INVALID_OPERATION;
  }
}
