package com.example.proyectoari.domain.models.xml;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClientsXML {

    @JacksonXmlElementWrapper(useWrapping = false)
    private List<ClientXML> clientList;
}
