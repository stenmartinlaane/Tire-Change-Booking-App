package com.stenmartin.project.booking_backend.dal;

import com.stenmartin.project.booking_backend.domain.DomainResponse;
import com.stenmartin.project.booking_backend.domain.entity.TireWorkshop;
import com.stenmartin.project.booking_backend.domain.entity.VehicleType;
import com.stenmartin.project.booking_backend.domain.repository.TireChangeTimeRepository;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring")
public abstract class AutoMapper {

    final TireChangeTimeRepositoryProvider tireChangeTimeRepositoryProvider;

    public AutoMapper(TireChangeTimeRepositoryProvider tireChangeTimeRepositoryProvider) {
        this.tireChangeTimeRepositoryProvider = tireChangeTimeRepositoryProvider;
    }

    public abstract <T> DomainResponse<T> DalResponseToDomain(DalResponse<T> dalResponse);

    @Mapping(target = "repository", source = "id", qualifiedByName = "handleMissingRepository")
    public abstract TireWorkshop TireWorkshopToDomain(com.stenmartin.project.booking_backend.dal.entity.TireWorkshop tireWorkshop);

    public TireChangeTimeRepository handleMissingRepository(String id) {
        return tireChangeTimeRepositoryProvider.findTireChangeTimeRepositoryById(id);
    }

    public abstract VehicleType VehicleTypeToDomain(com.stenmartin.project.booking_backend.dal.entity.VehicleType vehicleType);
}
