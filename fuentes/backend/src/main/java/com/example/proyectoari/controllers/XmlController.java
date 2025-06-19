package com.example.proyectoari.controllers;

import com.example.proyectoari.domain.dtos.fileUploaders.TxtUploaderDTO;
import com.example.proyectoari.services.FileConvertersService;
import com.example.proyectoari.services.FileReadersService;
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
public class XmlController {

    private final FileConvertersService fileConvertersService;
    private final FileReadersService fileReadersService;

    public XmlController(FileConvertersService fileConvertersService, FileReadersService fileReadersService) {
        this.fileConvertersService = fileConvertersService;
        this.fileReadersService = fileReadersService;
    }

    @PostMapping("/txt-to-xml")
    public ResponseEntity<?> txtToXml(@ModelAttribute @Valid TxtUploaderDTO txtUploaderDTO) throws IOException {

        Iterable<CSVRecord> readText = fileReadersService.txtReader(txtUploaderDTO.getTxtFile(), txtUploaderDTO.getDelimiter());

        String newXML = fileConvertersService.fileConvertorTxtXML(readText, txtUploaderDTO.getEncryptionKey());

        ByteArrayResource resource = new ByteArrayResource(newXML.getBytes());

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"result.xml\"")
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .contentLength(resource.contentLength())
                .body(resource);
    }
}
