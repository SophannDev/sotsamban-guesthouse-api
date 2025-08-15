package com.sgh.sotsamban_guesthouse_api.dto.response.roomtype;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.sgh.sotsamban_guesthouse_api.common.Pagination;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Page;

import java.util.List;

@Setter
@Getter
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class RoomTypeMainResponse {

    List<RoomTypeResponse> roomTypes;
    Pagination page;

    @Builder
    public RoomTypeMainResponse(List<RoomTypeResponse> roomTypes, Page<?> page) {
        this.roomTypes = roomTypes;
        this.page = new Pagination(page);
    }
}
