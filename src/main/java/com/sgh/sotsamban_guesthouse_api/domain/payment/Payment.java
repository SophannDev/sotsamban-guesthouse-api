package com.sgh.sotsamban_guesthouse_api.domain.payment;

import com.sgh.sotsamban_guesthouse_api.domain.BaseEntity;
import com.sgh.sotsamban_guesthouse_api.domain.booking.Booking;
import com.sgh.sotsamban_guesthouse_api.enums.PaymentMethodStatus;
import com.sgh.sotsamban_guesthouse_api.enums.PaymentStatus;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
@Entity
@Table(name = "tb_payment")
@Getter
@Setter
@Builder
@NoArgsConstructor
public class Payment extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pay_id")
    private Long paymentId;

    @Column(name = "amt_paid", nullable = false)
    private BigDecimal amountPaid;

    @Convert(converter = PaymentMethodStatus.Converter.class)
    @Column(name = "pay_mthd", nullable = false)
    private PaymentMethodStatus paymentMethod;

    @Convert(converter = PaymentStatus.Converter.class)
    @Column(name = "pay_sts")
    private PaymentStatus paymentStatus = PaymentStatus.PENDING;

    @Column(name = "pay_dt")
    private LocalDateTime paymentDate = LocalDateTime.now();

    @Column(name = "notes", columnDefinition = "TEXT")
    private String notes;

    @OneToOne
    @JoinColumn(name = "bkg_id")
    private Booking booking;

    @Builder
    public Payment(Long paymentId, BigDecimal amountPaid, PaymentMethodStatus paymentMethod,
                   PaymentStatus paymentStatus, LocalDateTime paymentDate, String notes, Booking booking) {
        this.paymentId = paymentId;
        this.amountPaid = amountPaid;
        this.paymentMethod = paymentMethod;
        this.paymentStatus = paymentStatus;
        this.paymentDate = paymentDate;
        this.notes = notes;
        this.booking = booking;
    }

}
