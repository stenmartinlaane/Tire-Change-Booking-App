package com.stenmartin.project.booking_backend.domain.entity;

import com.stenmartin.project.booking_backend.domain.repository.TireChangeTimeRepository;
import com.stenmartin.project.booking_backend.dto.interfaces.TireChangeSchedulingResponse;
import com.stenmartin.project.booking_backend.dto.interfaces.TireChangeTimesResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.Set;
import java.util.concurrent.CompletableFuture;

@Data
@Builder
@AllArgsConstructor
public class TireWorkshop {
    private String id;
    private String name;
    private String address;
    private Set<VehicleType> supportedVehicleTypes;
    private TireChangeTimeRepository repository;

    public TireWorkshop(TireChangeTimeRepository repository) {
        this.repository = repository;
    }

    public CompletableFuture<TireChangeTimesResponse> getTireChangeTimesASync(String from, String to) {
        return repository.findAllAsync(from, to);
    }

    public TireChangeSchedulingResponse scheduleTireChangeTime(String bookingId, String contactInfo) {
        return repository.scheduleTireChange(bookingId, contactInfo);
    }
}
