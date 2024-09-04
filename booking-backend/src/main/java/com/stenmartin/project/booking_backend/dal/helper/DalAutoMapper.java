package com.stenmartin.project.booking_backend.dal.helper;

import com.stenmartin.project.booking_backend.dal.model.DalResponse;
import com.stenmartin.project.booking_backend.domain.entity.TireChangeTime;
import com.stenmartin.project.booking_backend.domain.entity.TireWorkshop;
import com.stenmartin.project.booking_backend.domain.entity.VehicleType;
import com.stenmartin.project.booking_backend.domain.model.DomainResponse;
import com.stenmartin.project.booking_backend.domain.model.TireChangeSchedulingResponse;
import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Set;

@Mapper(componentModel = "spring")
public abstract class DalAutoMapper {

    @Autowired
    protected TireChangeTimeRepositoryProvider tireChangeTimeRepositoryProvider;


    public abstract DomainResponse<List<TireChangeTime>> mapTireChangeTimeListToDomain(DalResponse<List<com.stenmartin.project.booking_backend.dal.entity.TireChangeTime>> timesResponse);

    //    @Mapping(target = "repository", source = "id", qualifiedByName = "handleMissingRepository")
    public TireWorkshop mapToDomain(com.stenmartin.project.booking_backend.dal.entity.TireWorkshop tireWorkshop) {
        return TireWorkshop.builder()
                .id(tireWorkshop.getId())
                .name(tireWorkshop.getName())
                .address(tireWorkshop.getAddress())
                .supportedVehicleTypes(mapToDomain(tireWorkshop.getSupportedVehicleTypes()))
                .repository(tireChangeTimeRepositoryProvider.findTireChangeTimeRepositoryById(tireWorkshop.getId()))
                .build();
    }


    public abstract DomainResponse<TireChangeSchedulingResponse> mapToDomain(DalResponse<com.stenmartin.project.booking_backend.dal.model.TireChangeSchedulingResponse> tireChangeSchedulingResponse);

    public abstract VehicleType VehicleTypeToDomain(com.stenmartin.project.booking_backend.dal.entity.VehicleType vehicleType);

    public abstract Set<VehicleType> mapToDomain(Set<com.stenmartin.project.booking_backend.dal.entity.VehicleType> vehicleTypes);

    public abstract TireChangeSchedulingResponse TireChangeSchedulingResponseToDomain(com.stenmartin.project.booking_backend.dal.model.TireChangeSchedulingResponse tireChangeSchedulingResponse);

    public abstract List<TireWorkshop> mapToDomain(List<com.stenmartin.project.booking_backend.dal.entity.TireWorkshop> tireWorkshops);

    public abstract TireChangeTime mapToDomain(com.stenmartin.project.booking_backend.dal.entity.TireChangeTime tireChangeTime);

    public abstract DomainResponse<List<TireChangeTime>> mapToDomainTireChangeTimes(DalResponse<List<com.stenmartin.project.booking_backend.dal.entity.TireChangeTime>> dalResponse);
}
