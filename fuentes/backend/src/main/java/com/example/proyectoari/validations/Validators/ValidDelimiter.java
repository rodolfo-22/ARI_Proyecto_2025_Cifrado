package com.example.proyectoari.validations.Validators;

import com.example.proyectoari.validations.DelimiterValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = DelimiterValidator.class)
@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidDelimiter {
    String message() default "The delimiter must be a special character (no letters or numbers)";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
