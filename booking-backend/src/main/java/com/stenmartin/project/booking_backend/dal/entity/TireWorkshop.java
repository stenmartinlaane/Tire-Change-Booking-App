package com.stenmartin.project.booking_backend.dal.entity;

import com.stenmartin.project.booking_backend.dal.entity.VehicleType;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;
import java.util.Set;

@AllArgsConstructor
@Data
public class TireWorkshop {
    private String id;
    private String name;
    private String address;
    private String baseUrl;
    private String apiVersion;
    private Set<VehicleType> supportedVehicleTypes;
}
