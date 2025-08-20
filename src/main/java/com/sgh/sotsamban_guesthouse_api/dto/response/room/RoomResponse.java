package com.sgh.sotsamban_guesthouse_api.dto.response.room;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class RoomResponse {

    private Long roomId;
    private String roomNumber;
    private String roomTypeId;
    private String roomTypeName;
    private String status;
    private String statusLabel;
    private BigDecimal pricePerNight;
    private BigDecimal basePrice;
    private String image;


    @Builder
    public RoomResponse(Long roomId, String roomNumber, String roomTypeId, String roomTypeName, String status, String statusLabel, BigDecimal pricePerNight, BigDecimal basePrice, String image) {
        this.roomId = roomId;
        this.roomNumber = roomNumber;
        this.roomTypeId = roomTypeId;
        this.roomTypeName = roomTypeName;
        this.status = status;
        this.statusLabel = statusLabel;
        this.pricePerNight = pricePerNight;
        this.basePrice = basePrice;
        this.image = image;
    }

}
