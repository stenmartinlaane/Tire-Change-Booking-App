package com.stenmartin.project.booking_backend.bll.services;

import com.stenmartin.project.booking_backend.bll.BllAutoMapper;
import com.stenmartin.project.booking_backend.domain.entity.TireWorkshop;
import com.stenmartin.project.booking_backend.domain.model.DomainResponse;
import com.stenmartin.project.booking_backend.domain.repository.TireWorkshopRepository;
import com.stenmartin.project.booking_backend.dto.entity.TireChangeTime;
import com.stenmartin.project.booking_backend.dto.model.ApiResponse;
import com.stenmartin.project.booking_backend.dto.model.TireChangeSchedulingResponse;
import com.stenmartin.project.booking_backend.dto.request.TireChangeSchedulingRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

@Service
public class TireChangeBookingService {

    final
    private BllAutoMapper autoMapper;
    private final List<TireWorkshop> tireWorkshops;

    public TireChangeBookingService(TireWorkshopRepository tireWorkshopRepository, BllAutoMapper autoMapper) {
        tireWorkshops = tireWorkshopRepository.findAll();
        this.autoMapper = autoMapper;
    }

    public List<ApiResponse<List<TireChangeTime>>> getTireChangeTimes(String from, String to) {
        var futures = new ArrayList<CompletableFuture<DomainResponse<List<com.stenmartin.project.booking_backend.domain.entity.TireChangeTime>>>>();
        for (TireWorkshop tireWorkshop : tireWorkshops) {
            futures.add(tireWorkshop.getTireChangeTimesASync(from, to));
        }
        CompletableFuture.allOf(futures.toArray(new CompletableFuture[0])).join();
        var result = futures.stream()
                .map(CompletableFuture::join)
                .collect(Collectors.toList());
        return autoMapper.mapToDto(result);
    }

    public ApiResponse<TireChangeSchedulingResponse> scheduleTireChangeTime(TireChangeSchedulingRequest request) {
        TireWorkshop workshop = tireWorkshops.stream()
                .filter(tireWorkshop -> Objects.equals(request.getTireWorkshopId(), tireWorkshop.getId()))
                .findFirst()
                .orElse(null);
        var res = workshop.scheduleTireChangeTime(request.getBookingId(), request.getTireWorkshopId());
        return autoMapper.mapToDtoTireChangeSchedulingResponse(res);
    }
}
