package com.example.proyectoari.validations.Validators;

import com.example.proyectoari.validations.XmlFileValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = XmlFileValidator.class)
@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidXmlFile {
    String message() default "The file must have xml format";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
