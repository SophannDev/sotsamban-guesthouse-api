package com.sgh.sotsamban_guesthouse_api.dto.request.room;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.sgh.sotsamban_guesthouse_api.enums.RoomStatus;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class RoomRequest {

    private String roomNumber;

    private Long roomTypeId;

    private RoomStatus status;

    private BigDecimal pricePerNight;

}
