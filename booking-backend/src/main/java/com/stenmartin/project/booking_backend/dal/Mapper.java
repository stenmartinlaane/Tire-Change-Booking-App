package com.stenmartin.project.booking_backend.dal;

import com.stenmartin.project.booking_backend.dto.entity.TireWorkshop;

import java.util.ArrayList;
import java.util.List;

public class Mapper {

    public static TireWorkshop tireWorkshopToDto(com.stenmartin.project.booking_backend.dal.entity.TireWorkshop tireWorkshop) {
        List<String> supportedVehicleTypes = new ArrayList<>();

        tireWorkshop.getSupportedVehicleTypes().forEach(supportedVehicleType -> {
            switch (supportedVehicleType) {
                case Car:
                    supportedVehicleTypes.add("Car");
                    break;
                case Truck:
                    supportedVehicleTypes.add("Truck");
                    break;
                default:
                    throw new IllegalArgumentException("Unknown Domain VehicleType: " + supportedVehicleType);
            }
        });

        return new TireWorkshop(
                tireWorkshop.getId(),
                tireWorkshop.getName(),
                tireWorkshop.getAddress(),
                supportedVehicleTypes
        );
    }
}
