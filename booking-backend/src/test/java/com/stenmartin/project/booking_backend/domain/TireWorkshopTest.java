package com.stenmartin.project.booking_backend.domain;

import com.stenmartin.project.booking_backend.domain.entity.TireWorkshop;
import com.stenmartin.project.booking_backend.domain.repository.TireWorkshopRepository;
import jakarta.annotation.PostConstruct;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class TireWorkshopTest {


    private TireWorkshopRepository tireWorkshopRepository;
    private List<TireWorkshop> tireWorkshops;

    @Autowired
    public TireWorkshopTest(TireWorkshopRepository tireWorkshopRepository) {
        this.tireWorkshopRepository = tireWorkshopRepository;
    }

    @PostConstruct
    public void init() {
        tireWorkshops = tireWorkshopRepository.findAll();
    }

    @Test
    public void getTireChangeTimesASync() {
        tireWorkshops.forEach(tireWorkshop -> {
            var result = tireWorkshop.getTireChangeTimesASync().join();
                System.out.println(result.getResult().size());
        });
    }

}
