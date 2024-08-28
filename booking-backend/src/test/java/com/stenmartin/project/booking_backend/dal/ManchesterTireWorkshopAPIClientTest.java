package com.stenmartin.project.booking_backend.dal;

import com.stenmartin.project.booking_backend.dal.api.LondonTireWorkshopAPIClient;
import com.stenmartin.project.booking_backend.dal.api.ManchesterTireWorkshopAPIClient;
import com.stenmartin.project.booking_backend.dal.api.TireWorkshopAPIClient;

public class ManchesterTireWorkshopAPIClientTest extends TireWorkshopAPIClientTestBase {
    @Override
    protected TireWorkshopAPIClient createClient() {
        return new ManchesterTireWorkshopAPIClient();
    }
}
