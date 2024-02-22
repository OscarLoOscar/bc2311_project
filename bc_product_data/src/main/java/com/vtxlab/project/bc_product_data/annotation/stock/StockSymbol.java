package com.vtxlab.project.bc_product_data.annotation.stock;

import java.util.Arrays;
import java.util.List;
import org.springframework.beans.factory.annotation.Value;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class StockSymbol {

  @Value("${api.bc_stock_finnhub.symbols}")
  private String stockSymbol;

  public List<String> getStockIds() {
    return Arrays.asList(stockSymbol.split(","));
  }

}
