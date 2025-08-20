package com.sgh.sotsamban_guesthouse_api.dto.request.payment;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class PaymentRequest {

    private Long bookingId;
    private String amountPaid;
    private Long paymentMethod;
    private Long paymentStatus;
    private String paymentDate;
    private String notes;

}
