package com.hsg.paymentservice.entity;

import jakarta.persistence.*;
import lombok.*;

import javax.validation.constraints.NotNull;
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
    @Column(length = 36, name = "approvalCode")
    private String approvalCode = UUID.randomUUID().toString();

    @Column(length = 10, name = "amount")
    private float amount;

    @Column(length = 4, name = "ccNo")
    private int creditCardNo;

    @Column(length = 5, name = "merchantId")
    private String merchantId;

    @Column(length = 5, name = "merchantPosId")
    private String merchantPosId;

    @Column(length = 16, name = "paymentDate")
    private LocalDateTime paymentDate;

    @Column(length = 5, name = "isReported")
    private Boolean isReported = false;

    @Column(length = 5, name = "confirmationCode")
    private int confirmationCode;

    @Column(length = 5, name = "isPaybackStatus")
    private Boolean isPaybackStatus = false;

    @Column(length = 5, name = "totalReportedAmount")
    private float totalReportedAmount;

    @Column(length = 5, name = "totalPaybackAmount")
    private float totalPaybackAmount;

    public void setPaymentDate(LocalDateTime paymentDate) {
        this.paymentDate = LocalDateTime.now();
    }

    public LocalDateTime getPaymentDate() {
        return paymentDate;
    }

}
