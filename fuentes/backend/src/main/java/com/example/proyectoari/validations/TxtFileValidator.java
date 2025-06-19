package com.example.proyectoari.validations;

import com.example.proyectoari.validations.Validators.ValidTxtFile;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.web.multipart.MultipartFile;

public class TxtFileValidator implements ConstraintValidator<ValidTxtFile, MultipartFile> {

    @Override
    public boolean isValid(MultipartFile file, ConstraintValidatorContext constraintValidatorContext) {
        if (file == null || file.isEmpty()) return false;

        String filename = file.getOriginalFilename();
        return filename != null && filename.toLowerCase().endsWith(".txt");
    }
}
