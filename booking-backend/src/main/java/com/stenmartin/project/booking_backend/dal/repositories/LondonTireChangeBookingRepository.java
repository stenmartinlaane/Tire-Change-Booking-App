package com.stenmartin.project.booking_backend.dal.repositories;

import com.stenmartin.project.booking_backend.dal.api.LondonTireWorkshopAPIClient;
import com.stenmartin.project.booking_backend.dal.api.TireWorkshopAPIClient;
import com.stenmartin.project.booking_backend.domain.repositoryInterfaces.TireChangeBookingRepository;
import com.stenmartin.project.booking_backend.domain.entity.TireChangeBooking;
import com.stenmartin.project.booking_backend.dto.TireChangeBookingResponse;

import java.util.List;

public class LondonTireChangeBookingRepository implements TireChangeBookingRepository {

    private final TireWorkshopAPIClient tireWorkshopAPIClient = new LondonTireWorkshopAPIClient();

    @Override
    public List<TireChangeBooking> findAll(String from, String to) {
        return tireWorkshopAPIClient.getTireChangeTimes(from, to);
    }

    @Override
    public TireChangeBookingResponse RegisterTireChangeBooking(String tireChangeBookingId, String contactInformation) {
        return tireWorkshopAPIClient.registerTireChangeBooking(tireChangeBookingId, contactInformation);
    }

    @Override
    public String getTireWorkshopId() {
        return tireWorkshopAPIClient.getTireWorkshopId();
    }

}
