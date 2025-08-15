package com.sgh.sotsamban_guesthouse_api.controller;

import com.sgh.sotsamban_guesthouse_api.common.MultiSortBuilder;
import com.sgh.sotsamban_guesthouse_api.dto.request.booking.BookingRequest;
import com.sgh.sotsamban_guesthouse_api.service.BookingService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping("/api/v1/bookings")
@RequiredArgsConstructor
@Slf4j
public class BookingController extends BaseRestController {

    private final BookingService bookingService;

     @PostMapping
     public Object createBooking(@Valid @RequestBody BookingRequest request) {
         bookingService.createBooking(request);
         return ok();
     }

     @GetMapping
        public Object getBookings(
             @RequestParam(value = "start_date", required = false) String startDate,
             @RequestParam(value = "end_date", required = false) String endDate,
             @RequestParam(value = "page_number", defaultValue = "0") int pageNumber,
             @RequestParam(value = "page_size", defaultValue = "10") int pageSize,
                @RequestParam(value = "search_value", required = false) String searchValue

     ) {

         List<Sort.Order> sortBuilder = new MultiSortBuilder().with(
                 "created_at:asc"
         ).build();
         Pageable pages = PageRequest.of(pageNumber, pageSize,Sort.by(sortBuilder));

         var bookingResp = bookingService.getBookings(startDate, endDate, searchValue, pages);

            return ok(bookingResp);
        }
}
