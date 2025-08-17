package com.sgh.sotsamban_guesthouse_api.dto.response.roomtype;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Builder
@Getter
@Setter
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class RoomTypeResponse {

    private Long roomTypeId;
    private String typeName;
    private String description;
    private BigDecimal basePrice;

    @Builder
    public RoomTypeResponse(Long roomTypeId, String typeName, String description, BigDecimal basePrice) {
        this.roomTypeId = roomTypeId;
        this.typeName = typeName;
        this.description = description;
        this.basePrice = basePrice;
    }
}
