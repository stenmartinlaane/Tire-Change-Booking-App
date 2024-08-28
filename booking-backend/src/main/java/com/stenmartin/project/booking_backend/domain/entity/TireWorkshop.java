package com.stenmartin.project.booking_backend.domain.entity;

import com.stenmartin.project.booking_backend.domain.repositoryInterfaces.TireChangeBookingRepository;
import com.stenmartin.project.booking_backend.domain.repositoryInterfaces.TireWorkshopRepository;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
public class TireWorkshop {
    private String id;
    private String name;
    private String address;
    private String BaseUrl;
    private List<VehicleType> SupportedVehicleTypes;
    private List<TireChangeBooking> tireChangeBookings;
    private TireChangeBookingRepository repository;

    public TireWorkshop(TireChangeBookingRepository repository) {
        this.repository = repository;
    }

//    public RegisterTireChangeBooking() {
//
//    }
}
