package com.stenmartin.project.booking_backend.dal.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class TireWorkshop {
    private String id;
    private String name;
    private String address;
    private String baseUrl;
    private String apiVersion;
    private Set<VehicleType> supportedVehicleTypes;
}
