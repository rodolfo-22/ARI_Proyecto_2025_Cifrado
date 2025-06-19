package com.example.proyectoari.services;

import com.example.proyectoari.domain.models.json.ClientsJSON;
import com.example.proyectoari.domain.models.xml.ClientsXML;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.apache.commons.csv.CSVRecord;

import java.io.IOException;

public interface FileConvertersService {
    String fileConvertorTxtXML(Iterable<CSVRecord> data, String encryptionKey) throws IOException;
    String fileConvertorTxtJSON(Iterable<CSVRecord> data, String encryptionKey);

    StringBuilder fileConvertorXMLTxt(ClientsXML clientsXMLList, String delimiter, String encryptionKey);
    StringBuilder fileConvertorJSONTxt(ClientsJSON clientsJSON, String delimiter, String encryptionKey) throws JsonProcessingException;
}
