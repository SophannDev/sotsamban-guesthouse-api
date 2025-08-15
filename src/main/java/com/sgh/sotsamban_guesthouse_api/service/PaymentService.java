package com.sgh.sotsamban_guesthouse_api.service;

import com.sgh.sotsamban_guesthouse_api.dto.request.payment.PaymentRequest;
import org.springframework.data.domain.Pageable;

public interface PaymentService {

    void createPayment(PaymentRequest request);

    Object getAllPayments(String searchValue, Pageable pageable);

}
