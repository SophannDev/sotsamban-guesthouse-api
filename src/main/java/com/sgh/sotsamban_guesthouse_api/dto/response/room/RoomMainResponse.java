package com.sgh.sotsamban_guesthouse_api.dto.response.room;

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
public class RoomMainResponse {

    List<RoomResponse> rooms;
    Pagination page;

    @Builder
    public RoomMainResponse(List<RoomResponse> rooms, Page<?> page) {
        this.rooms = rooms;
        this.page = new Pagination(page);
    }
}
