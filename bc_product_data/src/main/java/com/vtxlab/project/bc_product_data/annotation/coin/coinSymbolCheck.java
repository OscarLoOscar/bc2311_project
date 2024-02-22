package com.vtxlab.project.bc_product_data.annotation.coin;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Documented
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = coinSymbolValidator.class) // how to validate
public @interface coinSymbolCheck {
  
  public String message() default "Invalid Symbol. Please use a valid symbol to try again.";
  
  public Class<?>[] groups() default {};
  public Class<? extends Payload>[] payload() default{};
}
