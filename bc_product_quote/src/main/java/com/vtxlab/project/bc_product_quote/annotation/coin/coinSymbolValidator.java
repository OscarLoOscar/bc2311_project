package com.vtxlab.project.bc_product_quote.annotation.coin;

import java.util.Objects;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class coinSymbolValidator
    implements ConstraintValidator<coinSymbolCheck, CoinSymbol> {

  @Override
  public boolean isValid(CoinSymbol value, ConstraintValidatorContext context) {
    try {
      return Objects.nonNull(value) && value.getCoinIds().contains(value);
    } catch (Exception e) {
      return false;
    }
  }
}
