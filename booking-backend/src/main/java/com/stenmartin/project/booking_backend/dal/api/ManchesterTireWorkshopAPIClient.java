package com.stenmartin.project.booking_backend.dal.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.stenmartin.project.booking_backend.dal.base.BaseAPIClient;
import com.stenmartin.project.booking_backend.domain.entity.TireChangeBooking;
import com.stenmartin.project.booking_backend.domain.entity.TireWorkshop;
import com.stenmartin.project.booking_backend.dto.TireChangeBookingResponse;
import lombok.Getter;
import lombok.Setter;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class ManchesterTireWorkshopAPIClient extends BaseAPIClient implements TireWorkshopAPIClient {
    private TireWorkshop tireWorkshop;

    public ManchesterTireWorkshopAPIClient() {
        tireWorkshop = new TireWorkshop("2", "Manchester Workshop", "Manchester", "http://localhost:9004", List.of(), List.of());
    }

    @Override
    public List<TireChangeBooking> getTireChangeTimes(String from, String until) {
        String url = tireWorkshop.getBaseUrl() + "/api/v2/tire-change-times" + "?" + "from=" + from;
        List<TireChangeBooking> result = new ArrayList<>();

        try (HttpClient client = HttpClient.newHttpClient()) {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI(url))
                    .GET()
                    .header("Accept", "application/json")
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() == 200) {
                ObjectMapper objectMapper = new ObjectMapper();

                List<TireChangeTimesResponse> tireResponses = objectMapper.readValue(response.body(),
                        new TypeReference<>() {});
                tireResponses = tireResponses.stream().filter(TireChangeTimesResponse::isAvailable).toList();

                tireResponses.forEach(availableTime -> result.add(
                        new TireChangeBooking(
                                String.valueOf(availableTime.getId()),
                                tireWorkshop,
                                ZonedDateTime.parse(availableTime.getTime(), DateTimeFormatter.ISO_ZONED_DATE_TIME)
                        )
                ));

            } else {
                System.out.println("GET request failed. Response code: " + response.statusCode());
                System.out.println(response.body());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    @Override
    public TireChangeBookingResponse registerTireChangeBooking(String tireChangeBookingId, String contactInformation) {
        String url = tireWorkshop.getBaseUrl() + "/api/v2/tire-change-times/" + tireChangeBookingId + "/booking";

        TireChangeBookingRequest tireChangeBookingRequest = new TireChangeBookingRequest();
        tireChangeBookingRequest.setContactInformation(contactInformation);
        String jsonString = "";

        ObjectMapper objectMapper = new ObjectMapper();
        try {
            jsonString = objectMapper.writeValueAsString(tireChangeBookingRequest);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        System.out.println(jsonString);

        try(HttpClient client = HttpClient.newHttpClient()) {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI(url))
                    .POST(HttpRequest.BodyPublishers.ofString(jsonString))
                    .header("Content-Type", "application/json")
                    .header("Accept", "application/json")
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() == 200) { // HTTP OK
                objectMapper.readValue(response.body(), TireChangeTimesResponse.class);
            } else {
                System.out.println("GET request failed. Response code: " + response.statusCode());
                System.out.println(response.body());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public String getTireWorkshopId() {
        return tireWorkshop.getId();
    }


    @Setter
    @Getter
    private static class TireChangeBookingRequest {
        private String contactInformation;

        public String getContactInformation() {
            return contactInformation;
        }

    }

    @Setter
    @Getter
    private static class TireChangeTimesResponse {
        public boolean available;
        private int id;
        public String time;
    }
}
