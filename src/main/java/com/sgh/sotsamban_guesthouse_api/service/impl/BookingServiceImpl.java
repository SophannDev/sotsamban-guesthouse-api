package com.sgh.sotsamban_guesthouse_api.service.impl;

import com.sgh.sotsamban_guesthouse_api.domain.booking.Booking;
import com.sgh.sotsamban_guesthouse_api.domain.booking.BookingRepository;
import com.sgh.sotsamban_guesthouse_api.domain.room.RoomRepository;
import com.sgh.sotsamban_guesthouse_api.dto.request.booking.BookingRequest;
import com.sgh.sotsamban_guesthouse_api.dto.response.booking.BookingMainResponse;
import com.sgh.sotsamban_guesthouse_api.dto.response.booking.BookingResponse;
import com.sgh.sotsamban_guesthouse_api.dto.response.booking.IBooking;
import com.sgh.sotsamban_guesthouse_api.enums.BookingStatus;
import com.sgh.sotsamban_guesthouse_api.service.BookingService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class BookingServiceImpl implements BookingService {

    private final BookingRepository bookingRepository;
    private final RoomRepository roomRepository;

    @Override
    @Transactional
    public void createBooking(BookingRequest request) {

        var booking = Booking.builder()
                .actualCheckIn(request.getActualCheckIn())
                .actualCheckOut(request.getActualCheckOut())
                .bookingStatus(request.getBookingStatus() != null ? request.getBookingStatus() : BookingStatus.ACTIVE)
                .totalAmount(request.getTotalAmount())
                .notes(request.getNotes())
                .roomId(request.getRoomIds())
                .guestId(request.getGuestIds())
                .build();

        bookingRepository.save(booking);

    }

    @Override
    public Object getBookings(String startDate, String endDate, String searchValue, Pageable pageable) {

        Page<IBooking> findAllBookings = bookingRepository.findAllBookings(startDate, endDate, searchValue, pageable);

        var bookingResponses = findAllBookings.stream()
                .map(booking -> {

//                    var roomName = roomRepository.findRoomByRoomId(booking.getRoomId());

                    return BookingResponse.builder()
                            .bookingId(booking.getBookingId())
                            .roomId(booking.getRoomId())
                            .roomNumber(booking.getRoomNumber())
                            .guestId(booking.getGuestId())
                            .actualCheckIn(booking.getCheckIn())
                            .actualCheckOut(booking.getCheckOut())
                            .bookingStatus(booking.getSts())
                            .totalAmount(booking.getPricePerNight())
                            .notes(booking.getNoted())
                            .firstName(booking.getFirstName())
                            .lastName(booking.getLastName())
                            .build();
                })
                .toList();

        return BookingMainResponse.builder()
                .bookings(bookingResponses)
                .page(findAllBookings)
                .build();
    }
}
