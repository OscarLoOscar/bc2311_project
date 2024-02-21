package com.vtxlab.project.bc_stock_finnhub;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import com.vtxlab.project.bc_stock_finnhub.controller.impl.FinnhubController;
import com.vtxlab.project.bc_stock_finnhub.exception.ApiResp;
import com.vtxlab.project.bc_stock_finnhub.exception.exceptionEnum.Code;
import com.vtxlab.project.bc_stock_finnhub.model.CompanyProfile;
import com.vtxlab.project.bc_stock_finnhub.model.Quote;
import com.vtxlab.project.bc_stock_finnhub.model.StockDTO;
import com.vtxlab.project.bc_stock_finnhub.service.FinnhubService;

class FinnhubControllerTest {
  @Mock
  private FinnhubService finnhubService;

  @Spy
  private FinnhubController finnhubController = new FinnhubController();

  @BeforeEach
  public void setUp() {
    MockitoAnnotations.openMocks(this);
    when(finnhubController.symbolIsValid("AAPL")).thenReturn(true);
    when(finnhubController.symbolIsValid("GOOGL")).thenReturn(true);
    when(finnhubController.symbolIsValid("MSFT")).thenReturn(true);
    when(finnhubController.symbolIsValid("INVALID")).thenReturn(false);
    // when(finnhubController.getSymbolList())
    //     .thenReturn(List.of("AAPL,GOOGL,MSFT"));

    when(finnhubController.getSymbolList()).thenReturn(List.of("AAPL"));

  }


  @Test
  public void testGetQuote_InvalidSymbol_ReturnsInvalidInputApiResponse() {
    //my original   @Value("${api.finnhub.symbol}")  is private String symbolList;

    // Arrange
    String symbol = "INVALID";
    when(finnhubController.symbolIsValid(symbol)).thenReturn(false);

    // Act
    ApiResp<Quote> result = finnhubController.getQuote(symbol);

    // Assert
    assert result.getCode() == Code.INVALID_INPUT.getCode();
    assert result.getMessage().equals(Code.INVALID_INPUT.getMessage());
    assert result.getData() == null;
  }

  @Test
  public void testGetProfile_ValidSymbol_ReturnsApiResponseWithProfile() {
    // Arrange
    String symbol = "AAPL";
    CompanyProfile profile = CompanyProfile.builder().country("United States")//
        .currency("USD")//
        .estimateCurrency("USD")//
        .exchange("NASDAQ")//
        .finnhubIndustry("Technology")//
        .ipo("2021-01-01")//
        .logo("https://example.com/logo.png")//
        .marketCapitalization(1000000000L)//
        .name("Example Inc.")//
        .phone("+1 123-456-7890")//
        .shareOutstanding(100000000L)//
        .ticker("EXMP")//
        .weburl("https://example.com")//
        .build();
    when(finnhubService.getProfile(symbol)).thenReturn(profile);

    // Act
    ApiResp<CompanyProfile> result = finnhubController.getProfile(symbol);

    // Assert
    verify(finnhubService).getProfile(symbol);
    assert result.getCode() == Code.OK.getCode();
    assert result.getMessage().equals(Code.OK.getMessage());
    assert result.getData() == profile;
  }

  @Test
  public void testGetProfile_InvalidSymbol_ReturnsInvalidInputApiResponse() {
    // Arrange
    String symbol = "INVALID";
    when(finnhubController.symbolIsValid(symbol)).thenReturn(false);

    // Act
    ApiResp<CompanyProfile> result = finnhubController.getProfile(symbol);

    // Assert
    assert result.getCode() == Code.INVALID_INPUT.getCode();
    assert result.getMessage().equals(Code.INVALID_INPUT.getMessage());
    assert result.getData() == null;
  }

  @Test
  public void testGetStock_ValidSymbol_ReturnsApiResponseWithStock() {
    // Arrange
    String symbol = "AAPL";
    StockDTO stock = new StockDTO();
    when(finnhubService.getStock(symbol)).thenReturn(stock);

    // Act
    ApiResp<StockDTO> result = finnhubController.getStock(symbol);

    // Assert
    verify(finnhubService).getStock(symbol);
    assert result.getCode() == Code.OK.getCode();
    assert result.getMessage().equals(Code.OK.getMessage());
    assert result.getData() == stock;
  }

  @Test
  public void testGetStock_InvalidSymbol_ReturnsInvalidInputApiResponse() {
    // Arrange
    String symbol = "INVALID";
    when(finnhubController.symbolIsValid(symbol)).thenReturn(false);

    // Act
    ApiResp<StockDTO> result = finnhubController.getStock(symbol);

    // Assert
    assert result.getCode() == Code.INVALID_INPUT.getCode();
    assert result.getMessage().equals(Code.INVALID_INPUT.getMessage());
    assert result.getData() == null;
  }

  @Test
  public void testSymbolIsValid_ValidSymbol_ReturnsTrue() {
    // Arrange
    String symbol = "AAPL";

    // Act
    boolean result = finnhubController.symbolIsValid(symbol);

    // Assert
    assert result == true;
  }

  @Test
  public void testSymbolIsValid_InvalidSymbol_ReturnsFalse() {
    // Arrange
    String symbol = "INVALID";

    // Act
    boolean result = finnhubController.symbolIsValid(symbol);

    // Assert
    assert result == false;
  }
}
