package com.example.proyectoari.domain.dtos.fileUploaders;

import com.example.proyectoari.validations.Validators.ValidDelimiter;
import com.example.proyectoari.validations.Validators.ValidJsonFile;
import com.example.proyectoari.validations.Validators.ValidTxtFile;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class JsonUploaderDTO {

    @NotNull
    @ValidJsonFile
    private MultipartFile jsonFile;

    @NotBlank
    @ValidDelimiter
    private String delimiter;

    @NotBlank
    @Pattern(regexp = "^[a-zA-Z]+$", message = "The encryption key must only contain letters")
    private String encryptionKey;
}
