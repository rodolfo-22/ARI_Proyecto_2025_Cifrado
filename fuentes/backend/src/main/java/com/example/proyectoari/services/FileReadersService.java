package com.example.proyectoari.services;

import com.example.proyectoari.domain.models.json.ClientsJSON;
import com.example.proyectoari.domain.models.xml.ClientsXML;
import org.apache.commons.csv.CSVRecord;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface FileReadersService {
    Iterable<CSVRecord> txtReader(MultipartFile TxtFile, String delimiter) throws IOException;
    ClientsJSON jsonReader(MultipartFile jsonFile) throws IOException;
    ClientsXML xmlReader(MultipartFile xmlFile) throws IOException;
}