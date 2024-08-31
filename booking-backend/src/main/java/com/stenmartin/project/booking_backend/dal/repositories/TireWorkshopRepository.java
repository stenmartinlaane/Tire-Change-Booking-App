package com.stenmartin.project.booking_backend.dal.repositories;

import com.stenmartin.project.booking_backend.dal.helper.DalAutoMapper;
import com.stenmartin.project.booking_backend.dal.helper.TireWorkshopLoader;
import com.stenmartin.project.booking_backend.domain.entity.TireWorkshop;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TireWorkshopRepository implements com.stenmartin.project.booking_backend.domain.repository.TireWorkshopRepository {

    private final TireWorkshopLoader tireWorkshopLoader;
    private final DalAutoMapper autoMapper;

    public TireWorkshopRepository(TireWorkshopLoader tireWorkshopLoader, DalAutoMapper autoMapper) {
        this.tireWorkshopLoader = tireWorkshopLoader;
        this.autoMapper = autoMapper;
    }

    @Override
    public List<TireWorkshop> findAll() {
        return autoMapper.mapToDomain(tireWorkshopLoader.findAll());
    }

    @Override
    public TireWorkshop findById(String id) {
        return autoMapper.mapToDomain(tireWorkshopLoader.findById(id));
    }



}
