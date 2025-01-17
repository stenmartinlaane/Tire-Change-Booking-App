package com.stenmartin.project.booking_backend.dal.api;

import com.stenmartin.project.booking_backend.dal.Mapper;
import com.stenmartin.project.booking_backend.dal.base.BaseAPIClient;
import com.stenmartin.project.booking_backend.dal.entity.TireChangeTime;
import com.stenmartin.project.booking_backend.dal.entity.TireWorkshop;
import com.stenmartin.project.booking_backend.dal.helper.TireWorkshopLoader;
import com.stenmartin.project.booking_backend.dal.model.DalResponse;
import com.stenmartin.project.booking_backend.dal.model.TireChangeSchedulingResponse;
import jakarta.annotation.PostConstruct;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.StringReader;
import java.io.StringWriter;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@Component
public class LondonTireWorkshopAPIClient extends BaseAPIClient implements TireWorkshopAPIClient {
    private TireWorkshop tireWorkshop;
    @Getter
    private final String WORKSHOP_ID = "009d3f25-cd12-4bf1-abf1-ccbcf9fe736c";
    private final TireWorkshopLoader tireWorkshopLoader;

    @Autowired
    public LondonTireWorkshopAPIClient(TireWorkshopLoader tireWorkshopLoader) {
        this.tireWorkshopLoader = tireWorkshopLoader;
    }

    @PostConstruct
    public void init() {
        this.tireWorkshop = tireWorkshopLoader.findById(WORKSHOP_ID);
    }

    @Override
    public CompletableFuture<DalResponse<List<TireChangeTime>>> getTireChangeTimesAsync(String from, String until) {
        String url = tireWorkshop.getBaseUrl() + tireWorkshop.getApiVersion() + "tire-change-times/available" + "?" + "from=" + from + "&until=" + until;

        try (HttpClient client = HttpClient.newHttpClient()) {
            HttpRequest request = HttpRequest.newBuilder().uri(URI.create(url)).GET().header("Accept", "application/xml").build();

            return client.sendAsync(request, HttpResponse.BodyHandlers.ofString()).thenApply(response -> {
                List<TireChangeTime> result = new ArrayList<>();
                if (response.statusCode() == 200) {
                    try {
                        JAXBContext context = JAXBContext.newInstance(TireChangeTimesResponse.class);
                        Unmarshaller unmarshaller = context.createUnmarshaller();

                        TireChangeTimesResponse tireResponse = (TireChangeTimesResponse) unmarshaller.unmarshal(new StringReader(response.body()));
                        List<AvailableTime> availableTimes = tireResponse.getAvailableTimes();
                        if (availableTimes != null) {
                            availableTimes.forEach(availableTime -> result.add(new TireChangeTime(availableTime.uuid, Mapper.tireWorkshopToDto(tireWorkshop), ZonedDateTime.parse(availableTime.time, DateTimeFormatter.ISO_ZONED_DATE_TIME))));
                        }

                        return new DalResponse<List<TireChangeTime>>(result, "200", "success", true, tireWorkshop);
                    } catch (JAXBException e) {
                        e.printStackTrace();
                    }
                } else {
                    System.out.println("GET request failed. Response code: " + response.statusCode());
                }

                return new DalResponse<List<TireChangeTime>>(null, "500", "Exception", false, tireWorkshop);
            }).exceptionally(ex -> {
                ex.printStackTrace();
                return new DalResponse<List<TireChangeTime>>(null, "500", "Exception", false, tireWorkshop);
            });
        }
    }

    @Override
    public DalResponse<TireChangeSchedulingResponse> scheduleTireChange(String tireChangeBookingId, String contactInformation) {
        TireChangeSchedulingResponse result;

        String url = tireWorkshop.getBaseUrl() + tireWorkshop.getApiVersion() + "tire-change-times/" + tireChangeBookingId + "/booking";

        System.out.println(url);
        TireChangeBookingRequest tireChangeBookingRequest = new TireChangeBookingRequest();
        tireChangeBookingRequest.setContactInformation("string");

        JAXBContext context = null;
        String xmlString = "";
        try {



            context = JAXBContext.newInstance(TireChangeBookingRequest.class);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");

            // Marshal the object to a String
            StringWriter writer = new StringWriter();
            marshaller.marshal(tireChangeBookingRequest, writer);
            xmlString = writer.toString();
        } catch (JAXBException e) {
            throw new RuntimeException(e);
        }

        // Print or use the generated XML string
        try (HttpClient client = HttpClient.newHttpClient()) {
            HttpRequest request = HttpRequest.newBuilder().uri(new URI(url)).PUT(HttpRequest.BodyPublishers.ofString(xmlString)).header("Content-Type", "application/xml").header("Accept", "application/xml").build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() == 200) { // HTTP OK

            } else {
                System.out.println("GET request failed. Response code: " + response.statusCode());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new DalResponse<>(new TireChangeSchedulingResponse("tempt", "temp", tireWorkshop), "200", null, true, tireWorkshop);
    }

    @Setter
    @XmlRootElement(name = "availableTime")
    private static class AvailableTime {
        private String uuid;
        private String time;

        @XmlElement(name = "uuid")
        public String getUuid() {
            return uuid;
        }

        @XmlElement(name = "time")
        public String getTime() {
            return time;
        }

        @Override
        public String toString() {
            return "AvailableTime{" + "uuid='" + uuid + '\'' + ", time='" + time + '\'' + '}';
        }
    }

    @Setter
    @XmlRootElement(name = "tireChangeTimesResponse")
    private static class TireChangeTimesResponse {
        private List<AvailableTime> availableTimes;

        @XmlElement(name = "availableTime")
        public List<AvailableTime> getAvailableTimes() {
            return availableTimes;
        }

    }

    @Setter
    @XmlRootElement(name = "london.tireChangeBookingRequest")
    private static class TireChangeBookingRequest {
        private String contactInformation;

        @XmlElement(name = "contactInformation")
        public String getContactInformation() {
            return contactInformation;
        }

    }
}
