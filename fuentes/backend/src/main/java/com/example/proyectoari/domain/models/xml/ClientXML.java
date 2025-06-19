package com.example.proyectoari.domain.models.xml;

import com.example.proyectoari.utils.PolygonAsStringDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JacksonXmlRootElement(localName = "client")
public class ClientXML {
    @JacksonXmlProperty(localName = "document")
    private String document;

    @JacksonXmlProperty(localName = "name")
    private String name;

    @JacksonXmlProperty(localName = "lastName")
    private String lastName;

    @JacksonXmlProperty(localName = "cardNumber")
    private String cardNumber;

    @JacksonXmlProperty(localName = "cardType")
    private String cardType;

    @JacksonXmlProperty(localName = "phoneNumber")
    private String phoneNumber;

    @JacksonXmlProperty(localName = "polygon")
    private String polygon;
}
