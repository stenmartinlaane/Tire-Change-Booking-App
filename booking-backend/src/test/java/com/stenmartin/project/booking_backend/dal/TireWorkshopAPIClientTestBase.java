package com.stenmartin.project.booking_backend.dal;

import com.stenmartin.project.booking_backend.dal.api.TireWorkshopAPIClient;
import com.stenmartin.project.booking_backend.dto.entity.TireChangeTime;
import com.stenmartin.project.booking_backend.dto.interfaces.TireChangeTimesResponse;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public abstract class TireWorkshopAPIClientTestBase {

    protected abstract TireWorkshopAPIClient createClient();

    @Test
    public void getsAllOfTheData() {

        TireWorkshopAPIClient client = createClient();
        List<TireChangeTime> tireChangeBookings = getTireChangeTimesResponse("2006-01-02", "2030-01-02", client);
        tireChangeBookings.forEach(System.out::println);
        assertThat(tireChangeBookings).isNotNull();
    }

    @Test
    public void insertNewData() {
        TireWorkshopAPIClient client = createClient();
        List<TireChangeTime> tireChangeBookings = getTireChangeTimesResponse("2006-01-02", "2030-01-02", client);
        String id = tireChangeBookings.getFirst().getId(); // Use get(0) instead of getFirst()
        client.scheduleTireChange(id, "example@gmail.com");
        String secondId = getTireChangeTimesResponse("2006-01-02", "2030-01-02", client).getFirst().getId(); // Use get(0) instead of getFirst()
        assertThat(id).isNotEqualTo(secondId);
    }

    private List<TireChangeTime> getTireChangeTimesResponse(String from, String to, TireWorkshopAPIClient client) {
        TireChangeTimesResponse response = client.getTireChangeTimesAsync("2006-01-02", "2030-01-02").join();
        if (response.isSuccessful()) {
            var result = (com.stenmartin.project.booking_backend.dto.response.TireChangeTimesResponse) response;
            return result.getTireChangeTimes();
        } else {
            return List.of();
        }
    }
}
