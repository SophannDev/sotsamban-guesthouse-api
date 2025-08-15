package com.sgh.sotsamban_guesthouse_api.dto.response.reservation;

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
public class ReservationMainResponse {

    List<ReservationResponse> reservations;
    Pagination page;

    @Builder
    public ReservationMainResponse(List<ReservationResponse> reservations, Page<?> page) {
        this.reservations = reservations;
        this.page = new Pagination(page);
    }
}
