package com.stenmartin.project.booking_backend.dto.entity;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class TireWorkshop {
    private String id;
    private String name;
    private String address;
    private List<String> supportedVehicleTypes;
}
