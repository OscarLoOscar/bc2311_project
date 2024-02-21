package com.vtxlab.project.bc_stock_finnhub.controller.impl;

import java.util.Arrays;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.vtxlab.project.bc_stock_finnhub.controller.FinnhubOperation;
import com.vtxlab.project.bc_stock_finnhub.exception.ApiResp;
import com.vtxlab.project.bc_stock_finnhub.exception.exceptionEnum.Code;
import com.vtxlab.project.bc_stock_finnhub.model.CompanyProfile;
import com.vtxlab.project.bc_stock_finnhub.model.Quote;
import com.vtxlab.project.bc_stock_finnhub.model.StockDTO;
import com.vtxlab.project.bc_stock_finnhub.service.FinnhubService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/stock/finnhub/api/v1")
public class FinnhubController implements FinnhubOperation {

  @Autowired
  private FinnhubService finnhubService;

  @Value("${api.finnhub.symbol}")
  private String symbolList;

  @Override
  public ApiResp<Quote> getQuote(String symbol) {
    if (symbolIsValid(symbol)) {
      Quote data = finnhubService.getQuote(symbol);
      return ApiResp.<Quote>builder() //
          .code(Code.OK.getCode()) //
          .message(Code.OK.getMessage())//
          .data(data) //
          .build();
    }
    return ApiResp.<Quote>builder() //
        .code(Code.INVALID_INPUT.getCode()) //
        .message(Code.INVALID_INPUT.getMessage())//
        .build();
  }

  @Override
  public ApiResp<CompanyProfile> getProfile(String symbol) {
    if (symbolIsValid(symbol)) {
      CompanyProfile data = finnhubService.getProfile(symbol);
      return ApiResp.<CompanyProfile>builder() //
          .code(Code.OK.getCode()) //
          .message(Code.OK.getMessage())//
          .data(data) //
          .build();
    }
    return ApiResp.<CompanyProfile>builder() //
        .code(Code.INVALID_INPUT.getCode()) //
        .message(Code.INVALID_INPUT.getMessage())//
        .build();
  }

  @Override
  public ApiResp<StockDTO> getStock(String symbol) {
    if (symbolIsValid(symbol)) {
      StockDTO data = finnhubService.getStock(symbol);
      return ApiResp.<StockDTO>builder() //
          .code(Code.OK.getCode()) //
          .message(Code.OK.getMessage())//
          .data(data) //
          .build();
    }
    return ApiResp.<StockDTO>builder() //
        .code(Code.INVALID_INPUT.getCode()) //
        .message(Code.INVALID_INPUT.getMessage())//
        .build();
  }

  public boolean symbolIsValid(String symbol) {
    List<String> symbols = this.getSymbolList();
    log.info("SIZE : " + symbols.size());
    return symbols.contains(symbol);

  }

  public List<String> getSymbolList() {
    return Arrays.asList(symbolList.split(","));
  }
}
