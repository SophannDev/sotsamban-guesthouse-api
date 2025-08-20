package com.sgh.sotsamban_guesthouse_api.service.impl;

import com.sgh.sotsamban_guesthouse_api.common.StatusCode;
import com.sgh.sotsamban_guesthouse_api.domain.booking.Booking;
import com.sgh.sotsamban_guesthouse_api.domain.booking.BookingRepository;
import com.sgh.sotsamban_guesthouse_api.domain.room.RoomRepository;
import com.sgh.sotsamban_guesthouse_api.dto.request.booking.BookingRequest;
import com.sgh.sotsamban_guesthouse_api.dto.response.booking.BookingMainResponse;
import com.sgh.sotsamban_guesthouse_api.dto.response.booking.BookingResponse;
import com.sgh.sotsamban_guesthouse_api.dto.response.booking.IBooking;
import com.sgh.sotsamban_guesthouse_api.dto.response.room.RoomResponse;
import com.sgh.sotsamban_guesthouse_api.enums.BookingStatus;
import com.sgh.sotsamban_guesthouse_api.enums.RoomStatus;
import com.sgh.sotsamban_guesthouse_api.enums.RoomTypeStatus;
import com.sgh.sotsamban_guesthouse_api.exception.BusinessException;
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
                .bookingStatus(request.getBookingStatus() != null ? request.getBookingStatus() : BookingStatus.CONFIRMED)
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

                    var bookingStatusName = "";
                    var roomTypeName = "";

                    if (booking.getSts().equals("1")) {
                        bookingStatusName = BookingStatus.CONFIRMED.getLabel();
                    } else if (booking.getSts().equals("2")) {
                        bookingStatusName = BookingStatus.COMPLETED.getLabel();
                    } else if (booking.getSts().equals("0")) {
                        bookingStatusName = BookingStatus.CANCELLED.getLabel();
                    } else {
                        throw new BusinessException(StatusCode.NOT_FOUND, "Booking status not found");
                    }

                    if (booking.getRoomTypeName().equals("1")) {
                        roomTypeName = RoomTypeStatus.SINGLE_BED.getLabel();
                    } else if (booking.getRoomTypeName().equals("2")) {
                        roomTypeName = RoomTypeStatus.DOUBLE_BED.getLabel();
                    } else {
                        throw new BusinessException(StatusCode.NOT_FOUND, "Room type not found");
                    }

                    return BookingResponse.builder()
                            .bookingId(booking.getBookingId())
                            .roomId(booking.getRoomId())
                            .roomNumber(booking.getRoomNumber())
                            .guestId(booking.getGuestId())
                            .actualCheckIn(booking.getCheckIn())
                            .actualCheckOut(booking.getCheckOut())
                            .bookingStatus(booking.getSts())
                            .bookingStatusLabel(bookingStatusName)
                            .totalAmount(booking.getPricePerNight())
                            .notes(booking.getNoted())
                            .firstName(booking.getFirstName())
                            .lastName(booking.getLastName())
                            .roomTypeName(roomTypeName)
                            .build();
                })
                .toList();

        return BookingMainResponse.builder()
                .bookings(bookingResponses)
                .page(findAllBookings)
                .build();
    }
}
