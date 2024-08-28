package com.stenmartin.project.booking_backend.domain.repositoryInterfaces;

import com.stenmartin.project.booking_backend.domain.entity.TireWorkshop;

import java.util.List;

public interface TireWorkshopRepository {
    public List<TireWorkshop> findAll();
}
