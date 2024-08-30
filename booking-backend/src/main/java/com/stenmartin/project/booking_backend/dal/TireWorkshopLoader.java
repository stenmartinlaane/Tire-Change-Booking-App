package com.stenmartin.project.booking_backend.dal;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.stenmartin.project.booking_backend.domain.repository.TireChangeTimeRepository;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.*;

@Component
public class TireWorkshopLoader {
    private final List<TireWorkshop> workshops;

    public TireWorkshopLoader(ApplicationContext applicationContext) {
        List<TireWorkshop> workshops = new ArrayList<>();
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            InputStream inputStream = TireWorkshopLoader.class.getClassLoader().getResourceAsStream("workshops.json");
            JsonNode rootNode = objectMapper.readTree(inputStream);
            JsonNode workshopsNode = rootNode.path("Workshops");

            for (JsonNode workshopNode : workshopsNode) {
                String id = workshopNode.path("Id").asText();
                String name = workshopNode.path("Name").asText();
                String address = workshopNode.path("Address").asText();
                String baseUrl = workshopNode.path("BaseUrl").asText();
                String apiVersion = workshopNode.path("ApiVersion").asText();

                Set<VehicleType> supportedVehicleTypes = parseVehicleTypes(workshopNode.path("SupportedVehicleTypes"));

                TireWorkshop workshop = new TireWorkshop(
                        id,
                        name,
                        address,
                        baseUrl,
                        apiVersion,
                        supportedVehicleTypes
                );

                workshops.add(workshop);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.workshops = workshops;
    }

    private Set<VehicleType> parseVehicleTypes(JsonNode vehicleTypesNode) {
        Set<VehicleType> vehicleTypes = EnumSet.noneOf(VehicleType.class);
        if (vehicleTypesNode.isArray()) {
            for (JsonNode typeNode : vehicleTypesNode) {
                try {
                    VehicleType type = VehicleType.valueOf(typeNode.asText());
                    vehicleTypes.add(type);
                } catch (IllegalArgumentException e) {
                    System.out.println("Unknown vehicle type: " + typeNode.asText());
                }
            }
        }
        return vehicleTypes;
    }

//    private com.stenmartin.project.booking_backend.domain.entity.TireWorkshop workshopToDomain(TireWorkshop workshop) {
//        Set<com.stenmartin.project.booking_backend.domain.entity.VehicleType> supportedVehicleTypes = new HashSet<>() {
//        };
//
//        for (VehicleType vehicleType : workshop.getSupportedVehicleTypes()) {
//            switch (vehicleType) {
//                case Car:
//                    supportedVehicleTypes.add(com.stenmartin.project.booking_backend.domain.entity.VehicleType.Car);
//                    break;
//                case Truck:
//                    supportedVehicleTypes.add(com.stenmartin.project.booking_backend.domain.entity.VehicleType.Truck);
//                    break;
//                default:
//                    throw new IllegalArgumentException("Unknown Domain VehicleType: " + vehicleType);
//            }
//        }
//
//        return new com.stenmartin.project.booking_backend.domain.entity.TireWorkshop(
//                workshop.getId(),
//                workshop.getName(),
//                workshop.getAddress(),
//                supportedVehicleTypes,
//                findTireChangeTimeRepositoryById(workshop.getId()));
//    }

    private com.stenmartin.project.booking_backend.dal.entity.TireWorkshop workshopToDal(TireWorkshop workshop) {
        Set<com.stenmartin.project.booking_backend.dal.entity.VehicleType> supportedVehicleTypes = new HashSet<>() {
        };

        for (VehicleType vehicleType : workshop.getSupportedVehicleTypes()) {
            switch (vehicleType) {
                case Car:
                    supportedVehicleTypes.add(com.stenmartin.project.booking_backend.dal.entity.VehicleType.Car);
                    break;
                case Truck:
                    supportedVehicleTypes.add(com.stenmartin.project.booking_backend.dal.entity.VehicleType.Truck);
                    break;
                default:
                    throw new IllegalArgumentException("Unknown DAL VehicleType: " + vehicleType);
            }
        }

        return new com.stenmartin.project.booking_backend.dal.entity.TireWorkshop(
                workshop.getId(),
                workshop.getName(),
                workshop.getAddress(),
                workshop.getBaseUrl(),
                workshop.getApiVersion(),
                supportedVehicleTypes);
    }

//    public List<com.stenmartin.project.booking_backend.domain.entity.TireWorkshop> findAllDomain() {
//        List<com.stenmartin.project.booking_backend.domain.entity.TireWorkshop> results = new ArrayList<>();
//        for (TireWorkshop workshop : workshops) {
//            results.add(workshopToDomain(workshop));
//        }
//        return results;
//    }
//
//    public com.stenmartin.project.booking_backend.domain.entity.TireWorkshop findByIdDomain(String id) {
//        var result = workshops.stream().filter(tireWorkshop -> Objects.equals(tireWorkshop.getId(), id)).toList().getFirst();
//        return workshopToDomain(result);
//    }

    public com.stenmartin.project.booking_backend.dal.entity.TireWorkshop findById(String id) {
        var result = workshops.stream().filter(tireWorkshop -> Objects.equals(tireWorkshop.getId(), id)).toList().getFirst();
        return workshopToDal(result);
    }

    public List<com.stenmartin.project.booking_backend.dal.entity.TireWorkshop> findAll() {
        List<com.stenmartin.project.booking_backend.dal.entity.TireWorkshop> results = new ArrayList<>();
        for (TireWorkshop workshop : workshops) {
            results.add(workshopToDal(workshop));
        }
        return results;
    }


    @Getter
    @AllArgsConstructor
    private class TireWorkshop {
        private String id;
        private String name;
        private String address;
        private String baseUrl;
        private String apiVersion;
        private Set<com.stenmartin.project.booking_backend.dal.TireWorkshopLoader.VehicleType> supportedVehicleTypes;
    }


    private enum VehicleType {
        Car,
        Truck
    }

}
