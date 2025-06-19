package com.example.proyectoari.services.implementations;

import com.example.proyectoari.domain.models.json.ClientJSON;
import com.example.proyectoari.domain.models.json.ClientsJSON;
import com.example.proyectoari.domain.models.xml.ClientXML;
import com.example.proyectoari.domain.models.xml.ClientsXML;
import com.example.proyectoari.services.FileConvertersService;

import com.example.proyectoari.services.GeoEntitiesConverterService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.csv.CSVRecord;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

@Service
@Slf4j
public class FileConvertersServiceImpl implements FileConvertersService {

    private final ObjectMapper objectMapper;
    private final EncryptionServiceImpl encryptionService;
    private final DecryptionServiceImpl decryptionService;
    private final GeoEntitiesConverterService geoEntitiesConverterService;

    public FileConvertersServiceImpl(ObjectMapper objectMapper, EncryptionServiceImpl encryptionService, DecryptionServiceImpl decryptionService, GeoEntitiesConverterService geoEntitiesConverterService) {
        this.encryptionService = encryptionService;
        this.decryptionService = decryptionService;
        this.geoEntitiesConverterService = geoEntitiesConverterService;
        this.objectMapper = new ObjectMapper();
        this.objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
    }

    @Override
    public String fileConvertorTxtXML(Iterable<CSVRecord> data, String encryptionKey) throws IOException {
        List<ClientXML> clientsList = new ArrayList<>();

        for (CSVRecord r: data) {
            String encryptedCardNumber = encryptionService.vigenereEncrypt(r.get("cardNumber"), encryptionKey);
            String polygon = "POLYGON " + r.get("polygon");

            ClientXML client = new ClientXML();
            client.setDocument(r.get("document"));
            client.setName(r.get("name"));
            client.setLastName(r.get("lastName"));
            client.setCardNumber(encryptedCardNumber);
            client.setCardType(r.get("cardType"));
            client.setPhoneNumber(r.get("phoneNumber"));
            client.setPolygon(polygon);

            clientsList.add(client);
        }

        ClientsXML clientsXML = new ClientsXML(clientsList);

        XmlMapper xmlMapper = new XmlMapper();
        xmlMapper.enable(SerializationFeature.INDENT_OUTPUT);

        return xmlMapper.writeValueAsString(clientsXML);
    }

    @Override
    public String fileConvertorTxtJSON(Iterable<CSVRecord> data, String encryptionKey) {
        ArrayNode arrayNode = objectMapper.createArrayNode();

        for (CSVRecord object : data) {
            ObjectNode objectNode = objectMapper.createObjectNode();
            LinkedHashMap<String, String> jsonResult = new LinkedHashMap<>(object.toMap());

            jsonResult.forEach((key, value) -> {
                if ("cardNumber".equalsIgnoreCase(key)) {
                    String cardNumberEncrypted = encryptionService.vigenereEncrypt(value, encryptionKey);
                    objectNode.put(key, cardNumberEncrypted);
                } else if ("polygon".equalsIgnoreCase(key)) {
                    String polygon = geoEntitiesConverterService.wktToGeoJson(value);
                    try {
                        ObjectNode polygonNode = (ObjectNode) objectMapper.readTree(polygon);
                        objectNode.set(key, polygonNode);
                    } catch (IOException e) {
                        log.error("Error parsing polygon JSON", e);
                        objectNode.put(key, polygon);
                    }
                } else {
                    objectNode.put(key, value);
                }
            });

            arrayNode.add(objectNode);
        }

        ObjectNode root = objectMapper.createObjectNode();
        root.set("clientList", arrayNode);

        return root.toPrettyString();
    }

    @Override
    public StringBuilder fileConvertorXMLTxt(ClientsXML clientsXMLList, String delimiter, String encryptionKey) {

        StringBuilder builtString = new StringBuilder();
        builtString.append("document").append(delimiter)
                .append("name").append(delimiter)
                .append("lastName").append(delimiter)
                .append("cardNumber").append(delimiter)
                .append("cardType").append(delimiter)
                .append("phoneNumber").append(delimiter)
                .append("polygon").append("\n");

        for (ClientXML client : clientsXMLList.getClientList()) {
            String decryptedCardNumber = decryptionService.vigenereDecrypt(client.getCardNumber(), encryptionKey);
            String getPolygon = geoEntitiesConverterService.xmlToTxt(client.getPolygon());

            builtString.append(client.getDocument()).append(delimiter)
                    .append(client.getName()).append(delimiter)
                    .append(client.getLastName()).append(delimiter)
                    .append(decryptedCardNumber).append(delimiter)
                    .append(client.getCardType()).append(delimiter)
                    .append(client.getPhoneNumber()).append(delimiter)
                    .append(getPolygon).append("\n");
        }

        return builtString;
    }

    @Override
    public StringBuilder fileConvertorJSONTxt(ClientsJSON clientsJSON, String delimiter, String encryptionKey) throws JsonProcessingException {
        StringBuilder builtString = new StringBuilder();
        builtString.append("document").append(delimiter)
                .append("name").append(delimiter)
                .append("lastName").append(delimiter)
                .append("cardNumber").append(delimiter)
                .append("cardType").append(delimiter)
                .append("phoneNumber").append(delimiter)
                .append("polygon").append("\n");

        for (ClientJSON client : clientsJSON.getClientList()) {
            String decryptedCardNumber = decryptionService.vigenereDecrypt(client.getCardNumber(), encryptionKey);

            // Como 'polygon' ya es un JSON en string, lo convertimos a WKT con GeoJsonToTxt
            String getPolygon = geoEntitiesConverterService.GeoJsonToTxt(client.getPolygon());

            builtString.append(client.getDocument()).append(delimiter)
                    .append(client.getName()).append(delimiter)
                    .append(client.getLastName()).append(delimiter)
                    .append(decryptedCardNumber).append(delimiter)
                    .append(client.getCardType()).append(delimiter)
                    .append(client.getPhoneNumber()).append(delimiter)
                    .append(getPolygon).append("\n");
        }

        return builtString;
    }
}
