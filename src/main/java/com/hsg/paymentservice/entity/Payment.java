package com.hsg.paymentservice.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

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
    @Column(name = "paymentId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long paymentId;

    @Column( name = "paymentAmount")
    private float paymentAmount;

    @Column(name = "ccNo")
    private int creditCardNo;

    @Column(name = "paymentDate")
    private LocalDateTime paymentDate;

    @Column(name = "isReported")
    private Boolean isReported = false;

    @Column(name = "confirmationCode")
    private int confirmationCode;

    @Column(name = "totalReportedAmount")
    private float totalReportedAmount;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "merchant_pos_id",referencedColumnName = "merchantPosId")
    private MerchantPos merchantPos;

    @Column(name = "isPaybackStatus")
    private Boolean isPaybackStatus = false;

    public void setPaymentDate(LocalDateTime paymentDate) {
        this.paymentDate = LocalDateTime.now();
    }

    public LocalDateTime getPaymentDate() {
        return paymentDate;
    }

}
