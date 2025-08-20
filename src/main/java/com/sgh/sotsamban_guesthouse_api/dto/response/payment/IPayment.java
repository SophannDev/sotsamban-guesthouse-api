package com.sgh.sotsamban_guesthouse_api.dto.response.payment;

import org.springframework.beans.factory.annotation.Value;

public interface IPayment {

    @Value("#{target.pay_id}")
    Long getPaymentId();

    @Value("#{target.amt_paid}")
    String getAmountPaid();

    @Value("#{target.pay_mthd}")
    String getPaymentMethod();

    @Value("#{target.pay_sts}")
    String getPaymentStatus();

    @Value("#{target.pay_dt}")
    String getPaymentDate();

    @Value("#{target.notes}")
    String getNotes();

    @Value("#{target.bkg_id}")
    Long getBookingId();

    @Value("#{target.fname}")
    String getGuestFirstName();

    @Value("#{target.lname}")
    String getGuestLastName();

    @Value("#{target.room_num}")
    String getRoomNumber();



}