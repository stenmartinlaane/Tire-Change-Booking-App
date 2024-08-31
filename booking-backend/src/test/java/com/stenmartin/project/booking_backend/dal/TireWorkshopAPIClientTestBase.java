package com.stenmartin.project.booking_backend.dal;

import com.stenmartin.project.booking_backend.dal.api.TireWorkshopAPIClient;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public abstract class TireWorkshopAPIClientTestBase {

    protected abstract TireWorkshopAPIClient createClient();

    @Test
    public void getsAllOfTheData() {
        TireWorkshopAPIClient client = createClient();
        var tireChangeTimes = client.getTireChangeTimesAsync("2006-01-02", "2030-01-02").join().getReult();
//        tireChangeTimes.forEach(System.out::println);
        assertThat(tireChangeTimes).isNotNull();
    }

    @Test
    public void insertNewData() {
        TireWorkshopAPIClient client = createClient();
        var tireChangeTimes = client.getTireChangeTimesAsync("2006-01-02", "2030-01-02").join().getReult();
        String id = tireChangeTimes.getFirst().getId(); // Use get(0) instead of getFirst()
        client.scheduleTireChange(id, "example@gmail.com");
        String secondId = client.getTireChangeTimesAsync("2006-01-02", "2030-01-02").join().getReult().getFirst().getId(); // Use get(0) instead of getFirst()
        assertThat(id).isNotEqualTo(secondId);
    }
}
