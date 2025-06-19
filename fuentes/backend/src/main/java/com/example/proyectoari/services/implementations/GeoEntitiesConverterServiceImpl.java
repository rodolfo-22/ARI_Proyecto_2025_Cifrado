package com.example.proyectoari.services.implementations;

import com.example.proyectoari.services.GeoEntitiesConverterService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class GeoEntitiesConverterServiceImpl implements GeoEntitiesConverterService {

    private final ObjectMapper objectMapper;

    public GeoEntitiesConverterServiceImpl(ObjectMapper objectMapper) {
        this.objectMapper = new ObjectMapper();
        this.objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
    }

    @Override
    public String wktToGeoJson(String coordinates) {
        ObjectNode geoJson = objectMapper.createObjectNode();
        geoJson.put("type", "FeatureCollection");

        // CRS EPSG:4326
        ObjectNode crsNode = objectMapper.createObjectNode();
        crsNode.put("type", "name");
        ObjectNode crsProps = objectMapper.createObjectNode();
        crsProps.put("name", "EPSG:4326");
        crsNode.set("properties", crsProps);
        geoJson.set("crs", crsNode);

        // Features array
        ArrayNode featuresArray = objectMapper.createArrayNode();
        ObjectNode feature = objectMapper.createObjectNode();
        feature.put("type", "Feature");

        // Geometry
        ObjectNode geometry = objectMapper.createObjectNode();
        geometry.put("type", "Polygon");

        // Parse coordinates desde WKT
        String coordText = coordinates
                .replace("((", "")
                .replace("))", "")
                .trim();

        String[] points = coordText.split(",");
        ArrayNode polygonCoordinates = objectMapper.createArrayNode();
        ArrayNode ring = objectMapper.createArrayNode();

        for (String point : points) {
            String[] coords = point.trim().split("\\s+");
            double lon = Double.parseDouble(coords[0]);
            double lat = Double.parseDouble(coords[1]);

            ArrayNode coordPair = objectMapper.createArrayNode();
            coordPair.add(lon);
            coordPair.add(lat);
            ring.add(coordPair);
        }

        polygonCoordinates.add(ring);
        geometry.set("coordinates", polygonCoordinates);

        // Agregar geometry y properties al feature
        feature.set("geometry", geometry);

        ObjectNode properties = objectMapper.createObjectNode();
        properties.put("Land_Use", "I");
        feature.set("properties", properties);

        featuresArray.add(feature);
        geoJson.set("features", featuresArray);

        return geoJson.toPrettyString();
    }

    @Override
    public String xmlToTxt(String coordinates) {

        return coordinates
                .replace("\n", "")
                .replace("\t", "")
                .replace("POLYGON ", "");
    }

    @Override
    public String GeoJsonToTxt(String geoJson) {
        try {
            JsonNode root = objectMapper.readTree(geoJson);
            JsonNode coordinatesNode = root.path("features").get(0).path("geometry").path("coordinates");

            if (coordinatesNode.isArray() && !coordinatesNode.isEmpty()) {
                JsonNode ring = coordinatesNode.get(0);
                List<String> coordinatePairs = new ArrayList<>();

                for (JsonNode coordPair : ring) {
                    double x = coordPair.get(0).asDouble();
                    double y = coordPair.get(1).asDouble();
                    coordinatePairs.add(String.format("%.6f %.6f", x, y));
                }

                return "((" + String.join(", ", coordinatePairs) + "))";
            }

            return "INVALID_POLYGON";
        } catch (Exception e) {
            // Puedes lanzar una excepción custom o devolver string de error
            return "INVALID_POLYGON";
        }
    }
}
