package com.example.proyectoari.validations;

import com.example.proyectoari.validations.Validators.ValidXmlFile;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.web.multipart.MultipartFile;

public class XmlFileValidator implements ConstraintValidator<ValidXmlFile, MultipartFile> {

    @Override
    public boolean isValid(MultipartFile file, ConstraintValidatorContext constraintValidatorContext) {
        if (file == null || file.isEmpty()) return false;

        String filename = file.getOriginalFilename();
        return filename != null && filename.toLowerCase().endsWith(".xml");
    }
}
