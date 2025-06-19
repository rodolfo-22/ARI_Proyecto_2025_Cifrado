package com.example.proyectoari.validations.Validators;

import com.example.proyectoari.validations.JsonFileValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = JsonFileValidator.class)
@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidJsonFile {
    String message() default "The file must have json format";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
