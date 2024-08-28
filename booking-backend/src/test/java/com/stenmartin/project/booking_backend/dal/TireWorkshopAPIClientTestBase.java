package com.stenmartin.project.booking_backend.dal;

import com.stenmartin.project.booking_backend.dal.api.TireWorkshopAPIClient;
import com.stenmartin.project.booking_backend.domain.entity.TireChangeBooking;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;

public abstract class TireWorkshopAPIClientTestBase {

    protected abstract TireWorkshopAPIClient createClient();

    @Test
    public void getsAllOfTheData() {
        TireWorkshopAPIClient client = createClient();
        List<TireChangeBooking> tireChangeBookings = client.getTireChangeTimes("2006-01-02", "2030-01-02");
//        tireChangeBookings.forEach(System.out::println);
        assertThat(tireChangeBookings).isNotNull();
    }

    @Test
    public void insertNewData() {
        TireWorkshopAPIClient client = createClient();
        List<TireChangeBooking> tireChangeBookings = client.getTireChangeTimes("2006-01-02", "2030-01-02");
        String id = tireChangeBookings.get(0).getId(); // Use get(0) instead of getFirst()
        client.registerTireChangeBooking(id, "example@gmail.com");
        String secondId = client.getTireChangeTimes("2006-01-02", "2030-01-02").get(0).getId(); // Use get(0) instead of getFirst()
        assertThat(id).isNotEqualTo(secondId);
    }
}
