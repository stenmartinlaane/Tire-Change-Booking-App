package com.stenmartin.project.booking_backend.dal.api;

import com.stenmartin.project.booking_backend.domain.entity.TireChangeBooking;
import com.stenmartin.project.booking_backend.dto.TireChangeBookingResponse;

import java.util.List;

public interface TireWorkshopAPIClient {
    public List<TireChangeBooking> getTireChangeTimes(String from, String until);
    public TireChangeBookingResponse registerTireChangeBooking(String tireChangeBookingId, String contactInformation);
    public String getTireWorkshopId();
}
