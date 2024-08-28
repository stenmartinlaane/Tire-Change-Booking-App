package com.stenmartin.project.booking_backend;

import com.stenmartin.project.booking_backend.dal.api.LondonTireWorkshopAPIClient;
import com.stenmartin.project.booking_backend.dal.api.TireWorkshopAPIClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TireChangeBookingAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(TireChangeBookingAppApplication.class, args);
	}

}
