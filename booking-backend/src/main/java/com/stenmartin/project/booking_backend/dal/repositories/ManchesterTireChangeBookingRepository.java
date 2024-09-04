package com.stenmartin.project.booking_backend.dal.repositories;

import com.stenmartin.project.booking_backend.dal.api.ManchesterTireWorkshopAPIClient;
import com.stenmartin.project.booking_backend.dal.helper.DalAutoMapper;
import com.stenmartin.project.booking_backend.domain.entity.TireChangeTime;
import com.stenmartin.project.booking_backend.domain.model.DomainResponse;
import com.stenmartin.project.booking_backend.domain.model.TireChangeSchedulingResponse;
import com.stenmartin.project.booking_backend.domain.repository.TireChangeTimeRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Repository
public class ManchesterTireChangeBookingRepository implements TireChangeTimeRepository {

    private final ManchesterTireWorkshopAPIClient tireWorkshopAPIClient;
    private final DalAutoMapper autoMapper;

    public ManchesterTireChangeBookingRepository(ManchesterTireWorkshopAPIClient tireWorkshopAPIClient, DalAutoMapper autoMapper) {
        this.tireWorkshopAPIClient = tireWorkshopAPIClient;
        this.autoMapper = autoMapper;
    }

    @Override
    public CompletableFuture<DomainResponse<List<TireChangeTime>>> findAllAsync(String from, String to) {
        return tireWorkshopAPIClient.getTireChangeTimesAsync(from, to).thenApply(autoMapper::mapToDomainTireChangeTimes);
    }

    @Override
    public DomainResponse<TireChangeSchedulingResponse> scheduleTireChange(String tireChangeTimeId, String contactInformation) {
        var res = tireWorkshopAPIClient.scheduleTireChange(tireChangeTimeId, contactInformation);
        return autoMapper.mapToDomain(res);
    }

    @Override
    public String getWORKSHOP_ID() {
        return tireWorkshopAPIClient.getWORKSHOP_ID();
    }
}
