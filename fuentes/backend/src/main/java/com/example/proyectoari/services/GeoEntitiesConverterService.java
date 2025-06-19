package com.example.proyectoari.services;

public interface GeoEntitiesConverterService {

    String wktToGeoJson(String coordinates);
    String xmlToTxt(String coordinates);
    String GeoJsonToTxt(String geoJson);
}
