package com.sgh.sotsamban_guesthouse_api.dto.request.roomtype;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.sgh.sotsamban_guesthouse_api.enums.RoomTypeStatus;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class RoomTypeRequest {

    private RoomTypeStatus typeName;

    private String description;

    private BigDecimal basePrice;

}
