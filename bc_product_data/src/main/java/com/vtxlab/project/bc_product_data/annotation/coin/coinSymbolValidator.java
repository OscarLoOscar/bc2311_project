package com.vtxlab.project.bc_product_data.annotation.coin;

import java.util.Objects;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class CoinSymbolValidator
    implements ConstraintValidator<CoinSymbolCheck, String> {

  @Override
  public boolean isValid(String value, ConstraintValidatorContext context) {
      return Objects.nonNull(value);
  }
}
