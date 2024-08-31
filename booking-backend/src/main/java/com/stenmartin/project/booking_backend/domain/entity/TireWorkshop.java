package com.stenmartin.project.booking_backend.domain.entity;

import com.stenmartin.project.booking_backend.domain.model.DomainResponse;
import com.stenmartin.project.booking_backend.domain.model.TireChangeSchedulingResponse;
import com.stenmartin.project.booking_backend.domain.repository.TireChangeTimeRepository;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;
import java.util.concurrent.CompletableFuture;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TireWorkshop {
    private String id;
    private String name;
    private String address;
    private Set<VehicleType> supportedVehicleTypes;
    private TireChangeTimeRepository repository;

    public TireWorkshop(TireChangeTimeRepository repository) {
        this.repository = repository;
    }

    public CompletableFuture<DomainResponse<List<TireChangeTime>>> getTireChangeTimesASync(String from, String to) {
        return repository.findAllAsync(from, to);
    }

    public DomainResponse<TireChangeSchedulingResponse> scheduleTireChangeTime(String bookingId, String contactInfo) {
        return repository.scheduleTireChange(bookingId, contactInfo);
    }
}
