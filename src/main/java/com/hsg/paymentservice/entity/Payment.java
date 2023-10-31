package com.hsg.paymentservice.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "payment")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@EqualsAndHashCode(of = {"id"})
@ToString
public class Payment {

    @Id
    @SequenceGenerator(name = "seq_payment", allocationSize = 1)
//    @GeneratedValue(generator = "seq_payment", strategy = GenerationType.SEQUENCE)
    private String approvalCode = UUID.randomUUID().toString();

    @Column(length = 10, name = "amount")
    private float amount;

    @Column(length = 4, name = "ccNo")
    private int creditCardNo;

    @Column(length = 5, name = "merchantId")
    private String merchantId;

    @Column(length = 16, name = "paymentDate")
    private LocalDateTime paymentDate;

    @Setter
    @Column(length = 5, name = "isReported")
    private Boolean isReported;
}
