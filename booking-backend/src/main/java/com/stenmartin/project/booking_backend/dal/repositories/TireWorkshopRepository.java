package com.stenmartin.project.booking_backend.dal.repositories;

import com.stenmartin.project.booking_backend.dal.TireWorkshopLoader;
import com.stenmartin.project.booking_backend.domain.entity.TireWorkshop;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TireWorkshopRepository implements com.stenmartin.project.booking_backend.domain.repository.TireWorkshopRepository {

    private final TireWorkshopLoader tireWorkshopLoader;

    public TireWorkshopRepository(TireWorkshopLoader tireWorkshopLoader) {
        this.tireWorkshopLoader = tireWorkshopLoader;
    }

    @Override
    public List<TireWorkshop> findAll() {
        return tireWorkshopLoader.findAllDomain();
    }

    @Override
    public TireWorkshop findById(String id) {
        return tireWorkshopLoader.findByIdDomain(id);
    }



}
