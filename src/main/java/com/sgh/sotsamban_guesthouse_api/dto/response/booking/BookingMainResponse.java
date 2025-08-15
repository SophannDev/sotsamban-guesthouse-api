package com.sgh.sotsamban_guesthouse_api.dto.response.booking;

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
public class BookingMainResponse {

    List<BookingResponse> bookings;
    Pagination page;

    @Builder
    public BookingMainResponse(List<BookingResponse> bookings, Page<?> page) {
        this.bookings = bookings;
        this.page = new Pagination(page);
    }
}