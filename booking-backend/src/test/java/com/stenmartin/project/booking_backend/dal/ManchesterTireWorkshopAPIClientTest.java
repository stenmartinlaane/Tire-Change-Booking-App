package com.stenmartin.project.booking_backend.dal;

import com.stenmartin.project.booking_backend.dal.api.LondonTireWorkshopAPIClient;
import com.stenmartin.project.booking_backend.dal.api.ManchesterTireWorkshopAPIClient;
import com.stenmartin.project.booking_backend.dal.api.TireWorkshopAPIClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


public class ManchesterTireWorkshopAPIClientTest extends TireWorkshopAPIClientTestBase {

    @Autowired
    private ManchesterTireWorkshopAPIClient manchesterTireWorkshopAPIClient;

    @Override
    protected TireWorkshopAPIClient createClient() {
        return manchesterTireWorkshopAPIClient;
    }
}
