package com.hsg.paymentservice.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Table(name = "payback")
@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode(of = {"id"})
@ToString
public class Payback {

    @Id
    @SequenceGenerator(name = "seq_payback", allocationSize = 1)
    @Column(name = "paybackId")
    private String paybackId = UUID.randomUUID().toString();

    @Column(name = "paybackAmount")
    private float paybackAmount;

    @Column(name = "profitAmount")
    private float profitAmount;

    @ManyToOne
    @JoinColumn(name = "merchantPosId")
    private MerchantPos merchantPos;
}
