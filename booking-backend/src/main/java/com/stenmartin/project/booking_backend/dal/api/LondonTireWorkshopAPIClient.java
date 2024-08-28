package com.stenmartin.project.booking_backend.dal.api;

import com.stenmartin.project.booking_backend.dal.base.BaseAPIClient;
import com.stenmartin.project.booking_backend.dal.repositories.LondonTireChangeBookingRepository;
import com.stenmartin.project.booking_backend.domain.entity.TireChangeBooking;
import com.stenmartin.project.booking_backend.domain.entity.TireWorkshop;
import com.stenmartin.project.booking_backend.dto.TireChangeBookingResponse;
import lombok.Setter;

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
public class LondonTireWorkshopAPIClient extends BaseAPIClient implements TireWorkshopAPIClient {
    private final TireWorkshop tireWorkshop;

    public LondonTireWorkshopAPIClient() {
        tireWorkshop = new TireWorkshop("1", "London Workshop", "London", "http://localhost:9003", List.of(), List.of(), new LondonTireChangeBookingRepository());
    }

    @Override
    public List<TireChangeBooking> getTireChangeTimes(String from, String until) {
        String url = tireWorkshop.getBaseUrl() + "/api/v1/tire-change-times/available" + "?" + "from=" + from + "&until=" + until;
        List<TireChangeBooking> result = new ArrayList<>();
        try(HttpClient client = HttpClient.newHttpClient()) {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI(url))
                    .GET()
                    .header("Accept", "application/xml")
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() == 200) {
                JAXBContext context = JAXBContext.newInstance(TireChangeTimesResponse.class);
                Unmarshaller unmarshaller = context.createUnmarshaller();

                TireChangeTimesResponse tireResponse = (TireChangeTimesResponse) unmarshaller.unmarshal(new StringReader(response.body()));

                List<AvailableTime> availableTimes = tireResponse.getAvailableTimes();
                availableTimes.forEach(availableTime -> result.add(
                        new TireChangeBooking(
                                availableTime.uuid,
                                tireWorkshop,
                                ZonedDateTime.parse(availableTime.time, DateTimeFormatter.ISO_ZONED_DATE_TIME)
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
        TireChangeBookingResponse result;

        String url = tireWorkshop.getBaseUrl() + "/api/v1/tire-change-times/" + tireChangeBookingId + "/booking";

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
        try(HttpClient client = HttpClient.newHttpClient()) {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI(url))
                    .PUT(HttpRequest.BodyPublishers.ofString(xmlString))
                    .header("Content-Type", "application/xml")
                    .header("Accept", "application/xml")
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() == 200) { // HTTP OK
//                result = new TireChangeBookingResponse();
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
            return "AvailableTime{" +
                    "uuid='" + uuid + '\'' +
                    ", time='" + time + '\'' +
                    '}';
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
