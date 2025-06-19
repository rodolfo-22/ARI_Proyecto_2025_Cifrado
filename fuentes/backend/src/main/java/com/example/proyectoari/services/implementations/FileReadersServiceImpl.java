package com.example.proyectoari.services.implementations;

import com.example.proyectoari.domain.models.json.ClientsJSON;
import com.example.proyectoari.domain.models.xml.ClientsXML;
import com.example.proyectoari.services.FileReadersService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;

@Service
@Slf4j
public class FileReadersServiceImpl implements FileReadersService {

    @Override
    public Iterable<CSVRecord> txtReader(MultipartFile TxtFile, String delimiter) throws IOException {
        Reader reader = new InputStreamReader(TxtFile.getInputStream());

        CSVFormat format = CSVFormat.DEFAULT.builder()
                .setHeader()
                .setSkipHeaderRecord(true)
                .setDelimiter(delimiter)
                .build();

        return format.parse(reader);
    }

    @Override
    public ClientsJSON jsonReader(MultipartFile jsonFile) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(jsonFile.getInputStream(), ClientsJSON.class);
    }

    @Override
    public ClientsXML xmlReader(MultipartFile xmlFile) throws IOException {
        XmlMapper xmlMapper = new XmlMapper();
        return xmlMapper.readValue(xmlFile.getInputStream(), ClientsXML.class);
    }
}
