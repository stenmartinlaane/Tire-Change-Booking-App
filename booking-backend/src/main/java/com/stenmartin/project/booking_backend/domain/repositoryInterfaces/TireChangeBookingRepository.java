package com.stenmartin.project.booking_backend.domain.repositoryInterfaces;

import com.stenmartin.project.booking_backend.domain.entity.TireChangeBooking;
import com.stenmartin.project.booking_backend.dto.TireChangeBookingResponse;

import java.util.List;

public interface TireChangeBookingRepository {
    public List<TireChangeBooking> findAll(String from, String to);
    public TireChangeBookingResponse RegisterTireChangeBooking(String tireChangeBookingId, String contactInformation);
    public String getTireWorkshopId();
}


