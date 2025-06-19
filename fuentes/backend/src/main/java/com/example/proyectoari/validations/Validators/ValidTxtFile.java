package com.example.proyectoari.validations.Validators;

import com.example.proyectoari.validations.TxtFileValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = TxtFileValidator.class)
@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidTxtFile {
    String message() default "The file must have txt format";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
