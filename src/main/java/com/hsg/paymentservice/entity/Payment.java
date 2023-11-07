package com.hsg.paymentservice.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "payment")
@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode(of = {"id"})
@ToString
public class Payment {

    @Id
    @SequenceGenerator(name = "seq_payment", allocationSize = 1)
    @Column(length = 5, name = "paymentId")
    private String paymentId;

    @Column(length = 10, name = "paymentAmount")
    private float paymentAmount;

    @Column(length = 4, name = "ccNo")
    private int creditCardNo;

    @Column(length = 16, name = "paymentDate")
    private LocalDateTime paymentDate;

    @Column(length = 5, name = "isReported")
    private Boolean isReported = false;

    @Column(length = 5, name = "confirmationCode")
    private int confirmationCode;

    @Column(length = 5, name = "totalReportedAmount")
    private float totalReportedAmount;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "merchant_pos_id")
    private MerchantPos merchantPos;

    @Column(length = 5, name = "isPaybackStatus")
    private Boolean isPaybackStatus = false;

    public void setPaymentDate(LocalDateTime paymentDate) {
        this.paymentDate = LocalDateTime.now();
    }

    public LocalDateTime getPaymentDate() {
        return paymentDate;
    }

}
