package com.sgh.sotsamban_guesthouse_api.dto.request.booking;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.sgh.sotsamban_guesthouse_api.enums.BookingStatus;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
@Getter
@Setter
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class BookingRequest {

    private String actualCheckIn;

    private String actualCheckOut;

    private BookingStatus bookingStatus = BookingStatus.ACTIVE;

    private BigDecimal totalAmount;

    private String notes;

    private Long guestIds;

    private Long roomIds;
}
