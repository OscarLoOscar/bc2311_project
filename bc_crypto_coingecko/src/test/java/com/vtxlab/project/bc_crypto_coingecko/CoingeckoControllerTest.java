package com.vtxlab.project.bc_crypto_coingecko;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import com.vtxlab.project.bc_crypto_coingecko.controller.impl.CoingeckoController;
import com.vtxlab.project.bc_crypto_coingecko.exception.ApiResp;
import com.vtxlab.project.bc_crypto_coingecko.exception.exceptionEnum.Syscode;
import com.vtxlab.project.bc_crypto_coingecko.model.Coingecko;
import com.vtxlab.project.bc_crypto_coingecko.service.CoingeckoService;

public class CoingeckoControllerTest {
  @InjectMocks
  private CoingeckoController controller;

  @Mock
  private CoingeckoService coingeckoService;

  @BeforeEach
  public void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  void testGetAllData() {
    // Mock data
    Coingecko btc = Coingecko.builder().id("bitcoin").symbol("btc")
        .name("Bitcoin").currentPrice(50000).build();
    Coingecko eth = Coingecko.builder().id("ethereum").symbol("eth")
        .name("Ethereum").currentPrice(3000).build();
    List<Coingecko> testData = new ArrayList<>(List.of(btc, eth));
    // Mock service response
    when(coingeckoService.getDataFromApi("usd", "btc,eth"))
        .thenReturn(testData);
    // Call the controller method
    ApiResp<List<Coingecko>> response = controller.getAllData("usd", "btc,eth");
    ResponseEntity<ApiResp<List<Coingecko>>> responseEntity =
        ResponseEntity.ok(response);
    // verify the response
    assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    assertEquals(Syscode.OK.getSyscode(), response.getSyscode());
    assertEquals(Syscode.OK.getMessage(), response.getMessage());
    assertEquals(testData, response.getData());

  }
}
