package com.stenmartin.project.booking_backend.bll;

import com.stenmartin.project.booking_backend.domain.entity.TireChangeBooking;

public class Mapper {
    public static com.stenmartin.project.booking_backend.dto.TireChangeBooking tireChangeBookingToDTO(TireChangeBooking tireChangeBooking) {
        return new com.stenmartin.project.booking_backend.dto.TireChangeBooking(
                tireChangeBooking.getId(),
                tireChangeBooking.getTireWorkshop().getName(),
                tireChangeBooking.getTireWorkshop().getAddress(),
                tireChangeBooking.getDateTime()
        );
    }
}
