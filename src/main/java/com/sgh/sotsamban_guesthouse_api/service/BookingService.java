package com.sgh.sotsamban_guesthouse_api.service;

import com.sgh.sotsamban_guesthouse_api.dto.request.booking.BookingRequest;
import org.springframework.data.domain.Pageable;

public interface BookingService {
    void createBooking(BookingRequest request);

    Object getBookings(String startDate, String endDate, String searchValue, Pageable pageable);
}
