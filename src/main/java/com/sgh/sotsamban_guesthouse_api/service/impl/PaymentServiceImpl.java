package com.sgh.sotsamban_guesthouse_api.service.impl;

import com.sgh.sotsamban_guesthouse_api.common.StatusCode;
import com.sgh.sotsamban_guesthouse_api.domain.booking.Booking;
import com.sgh.sotsamban_guesthouse_api.domain.booking.BookingRepository;
import com.sgh.sotsamban_guesthouse_api.domain.payment.Payment;
import com.sgh.sotsamban_guesthouse_api.domain.payment.PaymentRepository;
import com.sgh.sotsamban_guesthouse_api.dto.request.payment.PaymentRequest;
import com.sgh.sotsamban_guesthouse_api.dto.response.payment.IPayment;
import com.sgh.sotsamban_guesthouse_api.dto.response.payment.PaymentMainResponse;
import com.sgh.sotsamban_guesthouse_api.dto.response.payment.PaymentResponse;
import com.sgh.sotsamban_guesthouse_api.enums.PaymentMethodStatus;
import com.sgh.sotsamban_guesthouse_api.enums.PaymentStatus;
import com.sgh.sotsamban_guesthouse_api.exception.BusinessException;
import com.sgh.sotsamban_guesthouse_api.service.PaymentService;
import com.sgh.sotsamban_guesthouse_api.utils.DateUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository paymentRepository;

    private final BookingRepository bookingRepository;

    // Implement the createPayment method
    @Override
    public void createPayment(PaymentRequest request) {
            // 1. Validate the booking exists
            Booking booking = bookingRepository.findById(request.getBookingId())
                    .orElseThrow(() -> new BusinessException(StatusCode.NOT_FOUND, "Booking not found with ID: " + request.getBookingId()));

            // 2. Convert Long values to enums using fromValue method
            PaymentMethodStatus paymentMethod = PaymentMethodStatus.fromValue(String.valueOf(request.getPaymentMethod()));
            if (paymentMethod == null) {
                throw new IllegalArgumentException("Invalid payment method: " + request.getPaymentMethod());
            }

            PaymentStatus paymentStatus = PaymentStatus.fromValue(String.valueOf(request.getPaymentStatus()));
            if (paymentStatus == null) {
                throw new IllegalArgumentException("Invalid payment status: " + request.getPaymentStatus());
            }

            // 3. Parse amount paid
            BigDecimal amountPaid;
            try {
                amountPaid = new BigDecimal(request.getAmountPaid());
                if (amountPaid.compareTo(BigDecimal.ZERO) <= 0) {
                    throw new IllegalArgumentException("Amount paid must be greater than zero");
                }
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("Invalid amount format: " + request.getAmountPaid());
            }

            // 5. Check if payment already exists for this booking (if one-to-one relationship)
            Optional<Payment> existingPayment = paymentRepository.findByBooking_BookingId(request.getBookingId());
            if (existingPayment.isPresent()) {
                throw new IllegalStateException("Payment already exists for booking ID: " + request.getBookingId());
            }

        // 6. Create and save the payment
            Payment payment = Payment.builder()
                    .amountPaid(amountPaid)
                    .paymentMethod(paymentMethod)
                    .paymentStatus(paymentStatus)
                    .paymentDate(DateUtils.toCompactString(LocalDateTime.now()))
                    .notes(request.getNotes())
                    .booking(booking)
                    .build();

            paymentRepository.save(payment);

            // 7. Optional: Update booking status if payment is completed
//            if (PaymentStatus.COMPLETED.equals(paymentStatus)) {
                // You might want to update booking status to PAID or similar
                // booking.setBookingStatus(BookingStatus.PAID);
                // bookingRepository.save(booking);
//            }

    }

    @Override
    public Object getAllPayments(String searchValue, String payStatus, String payMethod, Pageable pageable) {

        Page<IPayment> payments = paymentRepository.findAllPaymentsWithFilters(
                searchValue,
                payStatus,
                payMethod,
                pageable
        );

        var payResp = payments.stream()
                .map(payment -> PaymentResponse.builder()
                        .id(payment.getPaymentId())
                        .amountPaid(payment.getAmountPaid())
                        .paymentMethod(payment.getPaymentMethod())
                        .paymentMethodName(PaymentMethodStatus.fromValue(payment.getPaymentMethod()).getLabel())
                        .paymentStatus(payment.getPaymentStatus())
                        .paymentStatusName(PaymentStatus.fromValue(payment.getPaymentStatus()).getLabel())
                        .paymentDate(payment.getPaymentDate())
                        .notes(payment.getNotes())
                        .bookingId(payment.getBookingId())
                        .guestFirstName(payment.getGuestFirstName())
                        .guestLastName(payment.getGuestLastName())
                        .roomNumber(payment.getRoomNumber())
                        .build()
                ).toList();

        return PaymentMainResponse.builder()
                .payments(payResp)
                .page(payments)
                .build();
    }
}
