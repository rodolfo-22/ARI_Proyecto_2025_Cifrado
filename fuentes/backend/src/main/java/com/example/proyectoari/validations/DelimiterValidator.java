package com.example.proyectoari.validations;

import com.example.proyectoari.validations.Validators.ValidDelimiter;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class DelimiterValidator implements ConstraintValidator<ValidDelimiter, String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return value != null && value.matches("^[^a-zA-Z0-9]$");
    }
}
