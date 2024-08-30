package com.stenmartin.project.booking_backend.bll;

import com.stenmartin.project.booking_backend.domain.entity.TireChangeTime;

public class Mapper {
    public static com.stenmartin.project.booking_backend.dto.entity.TireChangeTime tireChangeBookingToDTO(TireChangeTime tireChangeBooking) {
        return new com.stenmartin.project.booking_backend.dto.entity.TireChangeTime(
                tireChangeBooking.getId(),
                tireChangeBooking.getTireWorkshop().getName(),
                tireChangeBooking.getTireWorkshop().getAddress(),
                tireChangeBooking.getDateTime()
        );
    }
}
