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

    @NotNull(message = "Amount can not be null")
    @Column(length = 10, name = "amount")
    private float amount;

    @NotNull(message = "Credit Card can not be null")
    @Column(length = 4, name = "ccNo")
    private int creditCardNo;

    @NotNull(message = "Merchant Id can not be null")
    @Column(length = 5, name = "merchantId")
    private String merchantId;

    @Column(length = 16, name = "paymentDate")
    private LocalDateTime paymentDate;

    @Column(length = 5, name = "isReported")
    private Boolean isReported = false;

    public LocalDateTime getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(LocalDateTime paymentDate) {
        this.paymentDate = LocalDateTime.now();
    }

}
