package com.stenmartin.project.booking_backend.domain.repository;

import com.stenmartin.project.booking_backend.domain.entity.TireWorkshop;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TireWorkshopRepository {
    public List<TireWorkshop> findAll();

    public TireWorkshop findById(String id);
}
