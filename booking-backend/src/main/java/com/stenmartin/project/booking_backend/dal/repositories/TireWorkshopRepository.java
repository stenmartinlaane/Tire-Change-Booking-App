package com.stenmartin.project.booking_backend.dal.repositories;

import com.stenmartin.project.booking_backend.domain.entity.TireWorkshop;

import java.util.List;

public class TireWorkshopRepository implements com.stenmartin.project.booking_backend.domain.repositoryInterfaces.TireWorkshopRepository {

    @Override
    public List<TireWorkshop> findAll() {
        return List.of();
    }
}
