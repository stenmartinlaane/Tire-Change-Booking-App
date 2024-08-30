package com.stenmartin.project.booking_backend.dal;

import com.stenmartin.project.booking_backend.dal.api.LondonTireWorkshopAPIClient;
import com.stenmartin.project.booking_backend.dal.api.ManchesterTireWorkshopAPIClient;
import com.stenmartin.project.booking_backend.dal.api.TireWorkshopAPIClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static org.assertj.core.api.Assertions.assertThat;

public class LondonTireWorkshopAPIClientTest extends TireWorkshopAPIClientTestBase {

    @Autowired
    private LondonTireWorkshopAPIClient londonTireWorkshopAPIClient;

    @Override
    protected TireWorkshopAPIClient createClient() {
        return londonTireWorkshopAPIClient;
    }
}
