package com.stenmartin.project.booking_backend.config;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.stenmartin.project.booking_backend.domain.entity.TireWorkshop;
import org.springframework.context.annotation.Configuration;
import com.fasterxml.jackson.databind.JsonNode;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Configuration
public class WorkshopConfigLoader {
    public static TireWorkshop getTireWorkshop(String workshop) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(new File("workshop.json"));
        JsonNode workshopsNode = jsonNode.get("Workshops");
        String baseUrl = workshopsNode.get("BaseUrl").asText();
        String address = workshopsNode.get("Address").asText();
        String name = workshopsNode.get("Name").asText();
        String id = workshopsNode.get("Id").asText();
        return new TireWorkshop(id, name, address, baseUrl, List.of(), List.of());
    }
}
