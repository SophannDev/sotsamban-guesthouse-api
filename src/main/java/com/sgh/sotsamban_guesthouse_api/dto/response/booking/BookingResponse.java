package com.sgh.sotsamban_guesthouse_api.dto.response.booking;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.sgh.sotsamban_guesthouse_api.dto.response.room.RoomResponse;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Builder
@Getter
@Setter
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class BookingResponse {

    private Long bookingId;
    private Long roomId;
    private String roomNumber;
    private Long guestId;
    private String firstName;
    private String lastName;
    private String actualCheckIn;
    private String actualCheckOut;
    private String bookingStatus;
    private String bookingStatusLabel;
    private BigDecimal totalAmount;
    private String notes;
    private String roomTypeName;
    private RoomResponse roomResponse;

    @Builder
    public BookingResponse(Long bookingId, Long roomId, String roomNumber, Long guestId, String firstName, String LastName, String actualCheckIn, String actualCheckOut,
                           String bookingStatus, String bookingStatusLabel, BigDecimal totalAmount, String notes, String roomTypeName, RoomResponse roomResponse) {
        this.bookingId = bookingId;
        this.roomId = roomId;
        this.roomNumber = roomNumber;
        this.guestId = guestId;
        this.firstName = firstName;
        this.lastName = LastName;
        this.actualCheckIn = actualCheckIn;
        this.actualCheckOut = actualCheckOut;
        this.bookingStatus = bookingStatus;
        this.bookingStatusLabel = bookingStatusLabel;
        this.totalAmount = totalAmount;
        this.notes = notes;
        this.roomTypeName = roomTypeName;
        this.roomResponse = roomResponse;
    }
}
