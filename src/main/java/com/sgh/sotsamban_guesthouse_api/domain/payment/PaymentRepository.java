package com.sgh.sotsamban_guesthouse_api.domain.payment;

import com.sgh.sotsamban_guesthouse_api.dto.response.payment.IPayment;
import com.sgh.sotsamban_guesthouse_api.enums.PaymentMethodStatus;
import com.sgh.sotsamban_guesthouse_api.enums.PaymentStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface PaymentRepository extends JpaRepository<Payment, Long> {

    @Query(value = """
        select tbp.bkg_id AS booking_id,
               tbp.pay_id AS payment_id,
               tbp.pay_dt AS payment_date,
               tbp.amt_paid AS payment_amount,
               tbp.pay_sts AS payment_status,
               tbp.pay_mthd AS payment_method,
               tbg.fname AS first_name,
                 tbg.lname AS last_name
        from tb_payment tbp
        join tb_guest tbg on tbp.pay_id = tbg.guest_id
    """, nativeQuery = true)
    Page<Object> findAllPayments(String searchValue, Pageable pageable);

    Optional<Payment> findByBooking_BookingId(Long bookingId);

    @Query(value = """
        SELECT p.pay_id,
               p.amt_paid,
               p.pay_mthd,
               p.pay_sts,
               p.pay_dt,
               p.notes,
               p.bkg_id,
               b.guest_id,
               g.fname,
               g.lname,
               r.room_num,
               b.bkg_id
        FROM tb_payment p
                 LEFT JOIN tb_booking b ON p.bkg_id = b.bkg_id
                 LEFT JOIN tb_guest g ON b.guest_id = g.guest_id
                 LEFT JOIN tb_room r ON b.room_id = r.room_id
        WHERE (?1 IS NULL OR ?1 = ''
            OR p.notes LIKE CONCAT('%', ?1, '%'))
          AND (?2 IS NULL OR ?2 = '' OR p.pay_sts = ?2)
          AND (?3 IS NULL OR ?3 = '' OR p.pay_mthd = ?3)
        ORDER BY p.created_at DESC
        """, nativeQuery = true)
    Page<IPayment> findAllPaymentsWithFilters(
            String searchValue,
            String payStatus,
            String payMethod,
            Pageable pageable
    );

}
