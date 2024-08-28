package com.stenmartin.project.booking_backend.dal;

import com.stenmartin.project.booking_backend.dal.api.LondonTireWorkshopAPIClient;
import com.stenmartin.project.booking_backend.dal.api.TireWorkshopAPIClient;

import static org.assertj.core.api.Assertions.assertThat;

public class LondonTireWorkshopAPIClientTest extends TireWorkshopAPIClientTestBase {

    @Override
    protected TireWorkshopAPIClient createClient() {
        return new LondonTireWorkshopAPIClient();
    }
}
