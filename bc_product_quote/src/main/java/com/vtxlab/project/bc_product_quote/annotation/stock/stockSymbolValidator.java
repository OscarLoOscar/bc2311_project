package com.vtxlab.project.bc_product_quote.annotation.stock;

import java.util.Objects;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class stockSymbolValidator
    implements ConstraintValidator<stockSymbolCheck, StockSymbol> {

  @Override
  public boolean isValid(StockSymbol value, ConstraintValidatorContext context) {
    try {
      return Objects.nonNull(value) && value.getStockIds().contains(value);
    } catch (Exception e) {
      return false;
    }
  }
}
