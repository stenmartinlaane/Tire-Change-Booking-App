package com.stenmartin.project.booking_backend.dal;

import com.stenmartin.project.booking_backend.dal.helper.TireWorkshopLoader;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class TireWorkshopLoaderTest {

    @Autowired
    private TireWorkshopLoader tireWorkshopLoader;

    @Test
    public void loadTireWorkshop() {
        var result = tireWorkshopLoader.findAll();
        result.forEach(tireWorkshop -> {
            System.out.println(tireWorkshop.getId());
        });
        assertThat(!result.isEmpty());
    }
}
