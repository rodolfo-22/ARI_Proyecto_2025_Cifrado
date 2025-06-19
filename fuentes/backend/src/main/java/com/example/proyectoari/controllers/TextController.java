package com.example.proyectoari.controllers;

import com.example.proyectoari.domain.dtos.fileUploaders.JsonUploaderDTO;
import com.example.proyectoari.domain.dtos.fileUploaders.XmlUploaderDTO;
import com.example.proyectoari.domain.models.json.ClientsJSON;
import com.example.proyectoari.domain.models.xml.ClientsXML;
import com.example.proyectoari.services.FileConvertersService;
import com.example.proyectoari.services.FileReadersService;
import jakarta.validation.Valid;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/api/convertors")
public class TextController {

    private final FileReadersService fileReadersService;
    private final FileConvertersService fileConvertersService;

    public TextController(FileReadersService fileReadersService, FileConvertersService fileConvertersService) {
        this.fileReadersService = fileReadersService;
        this.fileConvertersService = fileConvertersService;
    }

    @PostMapping("/xml-to-txt")
    public ResponseEntity<?> xmlToTxt(@ModelAttribute @Valid XmlUploaderDTO xmlUploaderDTO) throws IOException {

        ClientsXML clientsXMLList = fileReadersService.xmlReader(xmlUploaderDTO.getXmlFile());

        StringBuilder convertedText = fileConvertersService.fileConvertorXMLTxt(clientsXMLList, xmlUploaderDTO.getDelimiter(), xmlUploaderDTO.getEncryptionKey());

        ByteArrayResource resource = new ByteArrayResource(convertedText.toString().getBytes());

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"result.txt\"")
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .contentLength(resource.contentLength())
                .body(resource);
    }

    @PostMapping("/json-to-txt")
    public ResponseEntity<?> jsonToTxt(@ModelAttribute @Valid JsonUploaderDTO jsonUploaderDTO) throws Exception {

        ClientsJSON clientsJSONList = fileReadersService.jsonReader(jsonUploaderDTO.getJsonFile());

        StringBuilder convertedText = fileConvertersService.fileConvertorJSONTxt(clientsJSONList, jsonUploaderDTO.getDelimiter(), jsonUploaderDTO.getEncryptionKey());

        ByteArrayResource resource = new ByteArrayResource(convertedText.toString().getBytes());

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"result.txt\"")
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .contentLength(resource.contentLength())
                .body(resource);
    }
}
