package com.example.proyectoari.controllers;

import com.example.proyectoari.domain.dtos.fileUploaders.TxtUploaderDTO;
import com.example.proyectoari.services.FileReadersService;
import com.example.proyectoari.services.FileConvertersService;
import jakarta.validation.Valid;
import org.apache.commons.csv.CSVRecord;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api/convertors")
public class JsonController {

    private final FileReadersService fileReadersService;
    private final FileConvertersService fileConvertersService;

    public JsonController(FileReadersService fileReadersService, FileConvertersService fileConvertersService) {
        this.fileReadersService = fileReadersService;
        this.fileConvertersService = fileConvertersService;
    }

    @PostMapping("/txt-to-json")
    public ResponseEntity<?> txtToJson(@ModelAttribute @Valid TxtUploaderDTO txtUploaderDTO) throws IOException {

        Iterable<CSVRecord> readText = fileReadersService.txtReader(txtUploaderDTO.getTxtFile(), txtUploaderDTO.getDelimiter());

        String newJSON = fileConvertersService.fileConvertorTxtJSON(readText, txtUploaderDTO.getEncryptionKey());

        ByteArrayResource resource = new ByteArrayResource(newJSON.getBytes());

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"result.json\"")
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .contentLength(resource.contentLength())
                .body(resource);
    }
}
