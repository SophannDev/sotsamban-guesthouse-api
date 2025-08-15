package com.sgh.sotsamban_guesthouse_api.service.impl;

import com.sgh.sotsamban_guesthouse_api.common.StatusCode;
import com.sgh.sotsamban_guesthouse_api.domain.booking.BookingRepository;
import com.sgh.sotsamban_guesthouse_api.domain.payment.Payment;
import com.sgh.sotsamban_guesthouse_api.domain.payment.PaymentRepository;
import com.sgh.sotsamban_guesthouse_api.dto.request.payment.PaymentRequest;
import com.sgh.sotsamban_guesthouse_api.enums.PaymentMethodStatus;
import com.sgh.sotsamban_guesthouse_api.exception.BusinessException;
import com.sgh.sotsamban_guesthouse_api.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository paymentRepository;

    private final BookingRepository bookingRepository;

    // Implement the createPayment method
    @Override
    public void createPayment(PaymentRequest request) {

        var bookingId = bookingRepository.findById(request.getBookingId()).orElseThrow(() -> new BusinessException(StatusCode.NOT_FOUND));

         Payment payment = new Payment();
         payment.setAmountPaid(new BigDecimal(request.getAmountPaid()));
         payment.setPaymentMethod(PaymentMethodStatus.valueOf(request.getPaymentMethod()));
         payment.setNotes(request.getNotes());
         payment.setBooking(bookingId);

         paymentRepository.save(payment);
    }

    @Override
    public Object getAllPayments(String searchValue, Pageable pageable) {

        var findAllPayments = paymentRepository.findAllPayments(searchValue, pageable);


        return null;
    }
}
