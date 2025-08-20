package com.sgh.sotsamban_guesthouse_api.dto.response.payment;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class PaymentResponse {

    private Long id;
    private Long bookingId;
    private String amountPaid;
    private String paymentMethod;
    private String paymentMethodName;
    private String paymentStatus;
    private String paymentStatusName;
    private String notes;
    private String guestFirstName;
    private String guestLastName;
    private String paymentDate;
    private String roomNumber;


    @Builder
    public PaymentResponse(Long id, Long bookingId, String amountPaid, String paymentMethod, String paymentMethodName, String paymentStatus, String paymentStatusName,
                           String notes, String guestFirstName, String guestLastName, String paymentDate, String roomNumber) {
        this.id = id;
        this.bookingId = bookingId;
        this.amountPaid = amountPaid;
        this.paymentMethod = paymentMethod;
        this.paymentMethodName = paymentMethodName;
        this.paymentStatus = paymentStatus;
        this.paymentStatusName = paymentStatusName;
        this.notes = notes;
        this.guestFirstName = guestFirstName;
        this.guestLastName = guestLastName;
        this.paymentDate = paymentDate;
        this.roomNumber = roomNumber;
    }

}
