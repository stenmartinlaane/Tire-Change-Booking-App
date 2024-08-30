package com.stenmartin.project.booking_backend.dal.repositories;

import com.stenmartin.project.booking_backend.dal.api.ManchesterTireWorkshopAPIClient;
import com.stenmartin.project.booking_backend.domain.repository.TireChangeTimeRepository;
import com.stenmartin.project.booking_backend.dto.response.TireChangeSchedulingResponse;
import com.stenmartin.project.booking_backend.dto.interfaces.TireChangeTimesResponse;
import org.springframework.stereotype.Repository;

import java.util.concurrent.CompletableFuture;

@Repository
public class ManchesterTireChangeBookingRepository implements TireChangeTimeRepository {

    private final ManchesterTireWorkshopAPIClient tireWorkshopAPIClient;

    public ManchesterTireChangeBookingRepository(ManchesterTireWorkshopAPIClient tireWorkshopAPIClient) {
        this.tireWorkshopAPIClient = tireWorkshopAPIClient;
    }

    @Override
    public CompletableFuture<TireChangeTimesResponse> findAllAsync(String from, String to) {
        return tireWorkshopAPIClient.getTireChangeTimesAsync(from, to);
    }

    @Override
    public TireChangeSchedulingResponse scheduleTireChange(String tireChangeTimeId, String contactInformation) {
        return tireWorkshopAPIClient.scheduleTireChange(tireChangeTimeId, contactInformation);
    }

    @Override
    public String getTireWorkshopId() {
        return tireWorkshopAPIClient.getTireWorkshopId();
    }
}
