package com.stenmartin.project.booking_backend.api.controllers;

import com.stenmartin.project.booking_backend.bll.services.TireChangeBookingService;
import com.stenmartin.project.booking_backend.dto.ErrorResponse;
import com.stenmartin.project.booking_backend.dto.TireChangeBooking;
import com.stenmartin.project.booking_backend.dto.TireChangeBookingRequest;
import com.stenmartin.project.booking_backend.dto.TireChangeBookingResponse;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
                            array = @ArraySchema(schema = @Schema(implementation = TireChangeBooking.class)))
            }),
            @ApiResponse(responseCode = "404", description = "No tire change bookings found.",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class))})
    })
    @GetMapping("/bookings")
    public List<TireChangeBooking> getTireChangeBookings(
            @RequestParam(value = "from", required = false, defaultValue = "2006-01-02") String from,
            @RequestParam(value = "to", required = false, defaultValue = "2030-01-02") String to
    ) {
        return tireChangeBookingService.getTireChangeBookings(from, to);
    }


    @ApiResponses({
            @ApiResponse(responseCode = "200", content = { @Content(mediaType = "application/json",
                    schema = @Schema(implementation = TireChangeBookingResponse.class)) }),
            @ApiResponse(responseCode = "422", description = "The tire change time has already been booked by another contact.",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class)) })
    })
    @PostMapping("/{workshopId}/booking/{bookingId}")
    public ResponseEntity<?> RegisterTireChangeBooking(@PathVariable String workshopId, @PathVariable String bookingId,
                                                       @RequestParam(value = "contactInfo", required = true) String contactInfo) {
        TireChangeBookingResponse response = tireChangeBookingService.registerTireChangeBooking(new TireChangeBookingRequest(workshopId, bookingId, contactInfo));
        return ResponseEntity.ok(response);
    }
}
