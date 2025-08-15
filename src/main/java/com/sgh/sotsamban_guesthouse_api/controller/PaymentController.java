package com.sgh.sotsamban_guesthouse_api.controller;

import com.sgh.sotsamban_guesthouse_api.common.MultiSortBuilder;
import com.sgh.sotsamban_guesthouse_api.dto.request.payment.PaymentRequest;
import com.sgh.sotsamban_guesthouse_api.service.PaymentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping("/api/v1/payments")
@RequiredArgsConstructor
@Slf4j
public class PaymentController extends BaseRestController {

    private final PaymentService paymentService;

     @PostMapping
     public Object createPayment(@RequestBody @Valid PaymentRequest paymentRequest) {
         paymentService.createPayment(paymentRequest);
         return ok();
     }
        @GetMapping
        public Object getAllPayments(
                @RequestParam(required = false) String searchValue,
                @RequestParam(defaultValue = "0") int pageNumber,
                @RequestParam(defaultValue = "10") int pageSize) {

            List<Sort.Order> sortBuilder = new MultiSortBuilder().with(
                    "created_at:asc"
            ).build();
            Pageable pages = PageRequest.of(pageNumber, pageSize,Sort.by(sortBuilder));

            var paymentsResponse = paymentService.getAllPayments(searchValue, pages);
            return ok(paymentsResponse);
        }
}
