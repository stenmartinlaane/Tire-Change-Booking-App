package com.stenmartin.project.booking_backend.bll.services;

import com.stenmartin.project.booking_backend.domain.entity.TireWorkshop;
import com.stenmartin.project.booking_backend.domain.repository.TireWorkshopRepository;
import com.stenmartin.project.booking_backend.dto.request.TireChangeSchedulingRequest;
import com.stenmartin.project.booking_backend.dto.response.TireChangeSchedulingResponse;
import com.stenmartin.project.booking_backend.dto.interfaces.TireChangeTimesResponse;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

@Service
public class TireChangeBookingService {

    private final List<TireWorkshop> tireWorkshops;
    public TireChangeBookingService(TireWorkshopRepository tireWorkshopRepository) {
        tireWorkshops = tireWorkshopRepository.findAll();
    }

    public List<TireChangeTimesResponse> getTireChangeTimes(String from, String to) {
        List<CompletableFuture<TireChangeTimesResponse>> futures = new ArrayList<>();
        for (TireWorkshop tireWorkshop : tireWorkshops) {
            futures.add(tireWorkshop.getTireChangeTimesASync(from, to));
        }
        CompletableFuture.allOf(futures.toArray(new CompletableFuture[0])).join();
        return futures.stream()
                .map(CompletableFuture::join)
                .collect(Collectors.toList());
    }

    public TireChangeSchedulingResponse scheduleTireChangeTime(TireChangeSchedulingRequest request) {
        TireWorkshop workshop = tireWorkshops.stream()
                .filter(tireWorkshop -> Objects.equals(request.getTireWorkshopId(), tireWorkshop.getId()))
                .findFirst()
                .orElse(null);
        workshop.scheduleTireChangeTime(request.getBookingId(), request.getTireWorkshopId());
        return null;
    }
}
