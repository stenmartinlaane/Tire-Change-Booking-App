package com.stenmartin.project.booking_backend.api.controllers;

import com.stenmartin.project.booking_backend.bll.services.TireChangeBookingService;
import com.stenmartin.project.booking_backend.dto.model.TireWorkshopTireChangeTimesResponse;
import com.stenmartin.project.booking_backend.dto.model.ErrorResponse;
import com.stenmartin.project.booking_backend.dto.model.TireChangeSchedulingResponse;
import com.stenmartin.project.booking_backend.dto.request.TireChangeSchedulingRequest;
import com.stenmartin.project.booking_backend.dto.entity.TireChangeTime;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RequestMapping("/tire-change-times")
@RestController
@ApiResponses({
        @ApiResponse(responseCode = "500", description = "Internal server error", content = { @Content(mediaType = "application/json",
                schema = @Schema(implementation = ErrorResponse.class)) }),
        @ApiResponse(responseCode = "400", description = "Bad Request", content = { @Content(mediaType = "application/json",
                schema = @Schema(implementation = ErrorResponse.class)) }),
})
public class TireChangeController {

    private final TireChangeBookingService tireChangeBookingService;

    public TireChangeController(TireChangeBookingService tireChangeBookingService) {
        this.tireChangeBookingService = tireChangeBookingService;
    }

    @ApiResponses({
            @ApiResponse(responseCode = "200", content = {
                    @Content(mediaType = "application/json",
                            array = @ArraySchema( // Indicates that the response is an array
                                    schema = @Schema(
                                            implementation = TireWorkshopTireChangeTimesResponse.class // Specifies the type of the array items
                                    )
                            ))
            }),
            @ApiResponse(responseCode = "404", description = "No tire change bookings found.",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class))})
    })
    @GetMapping("/bookings")
    public ResponseEntity<?> getTireChangeTimes(
            @RequestParam(value = "from", required = false, defaultValue = "2006-01-02") String from,
            @RequestParam(value = "to", required = false, defaultValue = "2030-01-02") String to
    ) {
        List<com.stenmartin.project.booking_backend.dto.model.ApiResponse<List<TireChangeTime>>> response = tireChangeBookingService.getTireChangeTimes(from, to);
        return ResponseEntity.ok(response);
    }


    @ApiResponses({
            @ApiResponse(responseCode = "200", content = { @Content(mediaType = "application/json",
                    schema = @Schema(implementation = TireChangeSchedulingResponse.class)) }),
            @ApiResponse(responseCode = "422", description = "The tire change time has already been booked by another contact.",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class)) })
    })
    @PostMapping("/{workshopId}/booking/{bookingId}")
    public ResponseEntity<?> scheduleTireChangeTime(@PathVariable String workshopId, @PathVariable String bookingId,
                                                       @RequestParam(value = "contactInfo", required = true) String contactInfo) {
        var response = tireChangeBookingService.scheduleTireChangeTime(new TireChangeSchedulingRequest(workshopId, bookingId, contactInfo));

        if (response.isSuccess()) {
            return ResponseEntity.ok(response.getReult());
        } else if (Objects.equals(response.getStatusCode(), "422")) {
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY) // Assuming response.getCode() returns a valid HttpStatus code
                    .body(response);
        }
        return ResponseEntity.badRequest().body(response);
    }
}
