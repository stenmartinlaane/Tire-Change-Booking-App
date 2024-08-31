package com.stenmartin.project.booking_backend.bll;

import com.stenmartin.project.booking_backend.domain.model.DomainResponse;
import com.stenmartin.project.booking_backend.dto.entity.TireChangeTime;
import com.stenmartin.project.booking_backend.dto.entity.TireWorkshop;
import com.stenmartin.project.booking_backend.dto.model.ApiResponse;
import com.stenmartin.project.booking_backend.dto.model.TireChangeSchedulingResponse;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public abstract class BllAutoMapper {
    public abstract List<ApiResponse<List<TireChangeTime>>> mapToDto(List<DomainResponse<List<com.stenmartin.project.booking_backend.domain.entity.TireChangeTime>>> domainResponses);
    public abstract ApiResponse<List<TireChangeTime>> mapToDto(DomainResponse<List<com.stenmartin.project.booking_backend.domain.entity.TireChangeTime>> domainResponses);
    public abstract List<TireChangeTime> mapToDtoTireChangeTimeList(List<com.stenmartin.project.booking_backend.domain.entity.TireChangeTime> domainResponse);
    public abstract TireChangeTime mapToDto(com.stenmartin.project.booking_backend.domain.entity.TireChangeTime domainResponse);

    public abstract ApiResponse<TireChangeSchedulingResponse> mapToDtoTireChangeSchedulingResponse(DomainResponse<com.stenmartin.project.booking_backend.domain.model.TireChangeSchedulingResponse> domainResponse);
    public abstract TireChangeSchedulingResponse mapToDto(com.stenmartin.project.booking_backend.domain.model.TireChangeSchedulingResponse domainResponse);

    public abstract TireWorkshop mapToDto(TireWorkshop domainResponse);
}
