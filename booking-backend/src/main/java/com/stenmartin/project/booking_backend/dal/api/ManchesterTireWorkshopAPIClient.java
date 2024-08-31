package com.stenmartin.project.booking_backend.dal.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.stenmartin.project.booking_backend.dal.model.DalResponse;
import com.stenmartin.project.booking_backend.dal.Mapper;
import com.stenmartin.project.booking_backend.dal.helper.TireWorkshopLoader;
import com.stenmartin.project.booking_backend.dal.base.BaseAPIClient;
import com.stenmartin.project.booking_backend.dal.entity.TireChangeTime;
import com.stenmartin.project.booking_backend.dal.entity.TireWorkshop;
import com.stenmartin.project.booking_backend.dal.model.TireChangeSchedulingResponse;
import jakarta.annotation.PostConstruct;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@Component
public class ManchesterTireWorkshopAPIClient extends BaseAPIClient implements TireWorkshopAPIClient {
    private TireWorkshop tireWorkshop;
    @Getter
    private final String WORKSHOP_ID = "e50dc51b-f8e8-4d0c-9921-667601c077f9";
    private final TireWorkshopLoader tireWorkshopLoader;

    @Autowired
    public ManchesterTireWorkshopAPIClient(TireWorkshopLoader tireWorkshopLoader) {
        this.tireWorkshopLoader = tireWorkshopLoader;
    }

    @PostConstruct
    public void init() {
        this.tireWorkshop = tireWorkshopLoader.findById(WORKSHOP_ID);
    }

    @Override
    public CompletableFuture<DalResponse<List<TireChangeTime>>> getTireChangeTimesAsync(String from, String until) {
        String url = tireWorkshop.getBaseUrl() + tireWorkshop.getApiVersion() + "tire-change-times" + "?" + "from=" + from;

        try (HttpClient client = HttpClient.newHttpClient()) {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI(url))
                    .GET()
                    .header("Accept", "application/json")
                    .build();


            return client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                    .thenApply(response -> {
                        List<TireChangeTime> result = new ArrayList<>();
                        if (response.statusCode() == 200) {
                            ObjectMapper objectMapper = new ObjectMapper();

                            List<TireChangeTimesResponse> tireResponses = null;
                            try {
                                tireResponses = objectMapper.readValue(response.body(),
                                        new TypeReference<>() {});
                            } catch (JsonProcessingException e) {
                                throw new RuntimeException(e);
                            }
                            tireResponses = tireResponses.stream().filter(TireChangeTimesResponse::isAvailable).toList();

                            tireResponses.forEach(availableTime -> result.add(
                                    new TireChangeTime(
                                            String.valueOf(availableTime.getId()),
                                            Mapper.tireWorkshopToDto(tireWorkshop),
                                            ZonedDateTime.parse(availableTime.getTime(), DateTimeFormatter.ISO_ZONED_DATE_TIME)
                                    )
                            ));
                            return new DalResponse<List<TireChangeTime>>(
                                    result,
                                    "200",
                                    null,
                                    true
                            );
                        } else {
                            System.out.println("GET request failed. Response code: " + response.statusCode());
                            System.out.println(response.body());
                            return new DalResponse<List<TireChangeTime>>(
                                    null,
                                    "500",
                                    "Exception",
                                    false
                            );
                        }
                            })
                    .exceptionally(ex -> {
                        ex.printStackTrace();
                        return new DalResponse<List<TireChangeTime>>(
                                null,
                                "500",
                                "Exception",
                                false
                        );
                    });
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public DalResponse<TireChangeSchedulingResponse> scheduleTireChange(String tireChangeBookingId, String contactInformation) {
        String url = tireWorkshop.getBaseUrl() + tireWorkshop.getApiVersion() + "tire-change-times/" + tireChangeBookingId + "/booking";

        TireChangeBookingRequest tireChangeBookingRequest = new TireChangeBookingRequest();
        tireChangeBookingRequest.setContactInformation(contactInformation);
        String jsonString = "";

        ObjectMapper objectMapper = new ObjectMapper();
        try {
            jsonString = objectMapper.writeValueAsString(tireChangeBookingRequest);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

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
        return new DalResponse<>(
                new TireChangeSchedulingResponse("tempt", "temp", tireWorkshop),
                "200",
                null,
                true
        );
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
