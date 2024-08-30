package com.stenmartin.project.booking_backend.dal;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class TireWorkshopLoaderTest {

    @Autowired
    private TireWorkshopLoader tireWorkshopLoader;

    @Test
    public void loadTireWorkshop() {
        tireWorkshopLoader.findAllDomain().forEach(tireWorkshop -> {
            System.out.println(tireWorkshop.getId());
        });
    }
}
