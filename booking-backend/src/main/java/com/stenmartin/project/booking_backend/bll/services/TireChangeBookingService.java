package com.stenmartin.project.booking_backend.bll.services;

import com.stenmartin.project.booking_backend.bll.Mapper;
import com.stenmartin.project.booking_backend.domain.repositoryInterfaces.TireChangeBookingRepository;
import com.stenmartin.project.booking_backend.dal.repositories.LondonTireChangeBookingRepository;
import com.stenmartin.project.booking_backend.dal.repositories.ManchesterTireChangeBookingRepository;
import com.stenmartin.project.booking_backend.dto.TireChangeBooking;
import com.stenmartin.project.booking_backend.dto.TireChangeBookingRequest;
import com.stenmartin.project.booking_backend.dto.TireChangeBookingResponse;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class TireChangeBookingService {

    private List<TireChangeBookingRepository> repositories;
    public TireChangeBookingService() {
        repositories = List.of(
                new LondonTireChangeBookingRepository(),
                new ManchesterTireChangeBookingRepository()
        );
    }

    public List<TireChangeBooking> getTireChangeBookings(String from, String to) {
        List<com.stenmartin.project.booking_backend.domain.entity.TireChangeBooking> tireChangeBookings = new ArrayList<>();
        repositories.forEach(repository -> {tireChangeBookings.addAll(repository.findAll(from, to));});
        return tireChangeBookings.stream().map(Mapper::tireChangeBookingToDTO).toList();
    }

    public TireChangeBookingResponse registerTireChangeBooking(TireChangeBookingRequest request) {
        List<TireChangeBookingRepository> filteredList =
                repositories.stream().filter(
                        repository -> Objects.equals(repository.getTireWorkshopId(), request.getTireWorkshopId())
                ).toList();
        if(filteredList.size() == 1) {
            TireChangeBookingRepository repository = filteredList.getFirst();
            repository.RegisterTireChangeBooking(request.getTireWorkshopId(), request. getContactInformation());
        } else {
            throw new RuntimeException();
        }
        return null;
    }
}
